/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.builder.guide

import jetbrains.datalore.base.gcommon.collect.ClosedRange
import jetbrains.datalore.base.geometry.DoubleRectangle
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.plot.base.render.svg.TextLabel
import jetbrains.datalore.plot.base.scale.Mappers
import jetbrains.datalore.plot.builder.scale.GuideBreak
import kotlin.math.max

abstract class ColorBarComponentLayout(
    title: String,
    private val myDomain: ClosedRange<Double>,
    protected val breaks: List<GuideBreak<Double>>,
    protected val guideBarSize: DoubleVector,
    legendDirection: LegendDirection
) :
        LegendBoxLayout(title, legendDirection) {

    private var myContentSize: DoubleVector? = null
    private val myBreakInfos = ArrayList<BreakInfo>()
    private lateinit var myBarBounds: DoubleRectangle

    public override val graphSize: DoubleVector
        get() {
            ensureInited()
            return myContentSize!!
        }

    internal val breakInfos: List<BreakInfo>
        get() {
            ensureInited()
            return myBreakInfos
        }

    val barBounds: DoubleRectangle
        get() {
            ensureInited()
            return myBarBounds
        }

    protected abstract val graphSizeIntern: DoubleVector

    protected abstract val guideBarLength: Double


    /**
     * @return num of pix added on each end of the bar (to avoid terminal ticks to lay on the border)
     */
    val barLengthExpand: Double
        get() = 2.0

    private fun ensureInited() {
        if (myContentSize == null) {
            doLayout()
        }
    }

    private fun doLayout() {
        val guideBarLength = guideBarLength
        val targetRange = ClosedRange(0.0 + barLengthExpand, guideBarLength - barLengthExpand)
        val mapper = Mappers.linear(myDomain, targetRange)

        for (br in breaks) {
            val tickLocation = mapper(br.domainValue)
            myBreakInfos.add(createBreakInfo(tickLocation))
        }

        myContentSize = graphSizeIntern
        myBarBounds = DoubleRectangle(DoubleVector.ZERO, guideBarSize)
    }

    internal abstract fun createBreakInfo(tickLocation: Double): BreakInfo

    internal class BreakInfo(
        val tickLocation: Double, val labelLocation: DoubleVector,
        val labelHorizontalAnchor: TextLabel.HorizontalAnchor, val labelVerticalAnchor: TextLabel.VerticalAnchor)

    private class MyHorizontal internal constructor(title: String, domain: ClosedRange<Double>, breaks: List<GuideBreak<Double>>, barSize: DoubleVector) : ColorBarComponentLayout(title, domain, breaks, barSize,
        LegendDirection.HORIZONTAL
    ) {

        private val labelDistance: Double
            get() = LABEL_SPEC.height() / 3

        override val guideBarLength: Double
            get() = guideBarSize.x

        override val graphSizeIntern: DoubleVector
            get() = DoubleVector(guideBarSize.x, guideBarSize.y + labelDistance + LABEL_SPEC.height())

        override fun createBreakInfo(tickLocation: Double): BreakInfo {
            val labelLocation = DoubleVector(tickLocation, guideBarSize.y + labelDistance)
            return BreakInfo(
                tickLocation,
                labelLocation,
                TextLabel.HorizontalAnchor.MIDDLE,
                TextLabel.VerticalAnchor.TOP
            )
        }
    }

    private class MyVertical internal constructor(title: String, domain: ClosedRange<Double>, breaks: List<GuideBreak<Double>>, barSize: DoubleVector) : ColorBarComponentLayout(title, domain, breaks, barSize,
        LegendDirection.VERTICAL
    ) {
        private var myMaxLabelWidth = 0.0

        private val labelDistance: Double
            get() = LABEL_SPEC.width(1) / 2

        override val guideBarLength: Double
            get() = guideBarSize.y

        override val graphSizeIntern: DoubleVector
            get() = DoubleVector(guideBarSize.x + labelDistance + myMaxLabelWidth, guideBarSize.y)

        init {
            for (br in breaks) {
                myMaxLabelWidth = max(myMaxLabelWidth, LABEL_SPEC.width(br.label.length))
            }
        }

        override fun createBreakInfo(tickLocation: Double): BreakInfo {
            val labelLocation = DoubleVector(guideBarSize.x + labelDistance, tickLocation)
            return BreakInfo(
                tickLocation,
                labelLocation,
                TextLabel.HorizontalAnchor.LEFT,
                TextLabel.VerticalAnchor.CENTER
            )
        }
    }

    companion object {
        fun horizontal(title: String, domain: ClosedRange<Double>, breaks: List<GuideBreak<Double>>, barSize: DoubleVector): ColorBarComponentLayout {
            return MyHorizontal(
                title,
                domain,
                breaks,
                barSize
            )
        }

        fun vertical(title: String, domain: ClosedRange<Double>, breaks: List<GuideBreak<Double>>, barSize: DoubleVector): ColorBarComponentLayout {
            return MyVertical(
                title,
                domain,
                breaks,
                barSize
            )
        }
    }
}
