package jetbrains.datalore.visualization.plot.gog.plot.guide

import jetbrains.datalore.base.gcommon.base.Strings
import jetbrains.datalore.base.geometry.DoubleRectangle
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.plot.gog.plot.theme.LegendTheme

abstract class LegendBoxSpec(val title: String, val theme: LegendTheme) {
    val contentOrigin: DoubleVector
    private val myFullContentExtend: DoubleVector

    private val myInnerOrigin: DoubleVector
    private val myInnerContentExtend: DoubleVector

    protected abstract val contentSize: DoubleVector

    val size: DoubleVector
        get() = contentSize.add(myFullContentExtend)

    val innerBounds: DoubleRectangle
        get() = DoubleRectangle(myInnerOrigin, contentSize.add(myInnerContentExtend))

    val contentBounds: DoubleRectangle
        get() = DoubleRectangle(contentOrigin, contentSize)

    internal abstract val layout: LegendBoxLayout

    protected val legendDirection: LegendDirection
        get() {
            var legendDirection = theme.direction()
            if (legendDirection === LegendDirection.AUTO) {
                val legendPosition = theme.position()
                legendDirection = if (legendPosition === LegendPosition.TOP || legendPosition === LegendPosition.BOTTOM)
                    LegendDirection.HORIZONTAL
                else
                    LegendDirection.VERTICAL
            }
            return legendDirection
        }

    init {

        val contentExpand = theme.margin() + theme.padding()
        contentOrigin = DoubleVector(contentExpand, contentExpand)
        myFullContentExtend = DoubleVector(contentExpand * 2, contentExpand * 2)

        myInnerOrigin = DoubleVector(theme.margin(), theme.margin())
        myInnerContentExtend = DoubleVector(theme.padding() * 2, theme.padding() * 2)
    }

    fun hasTitle(): Boolean {
        return !Strings.isNullOrEmpty(title)
    }
}
