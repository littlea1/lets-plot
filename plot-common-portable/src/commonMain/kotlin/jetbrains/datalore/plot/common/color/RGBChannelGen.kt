/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plot.common.color

import jetbrains.datalore.base.gcommon.collect.ClosedRange
import jetbrains.datalore.base.gcommon.collect.Ordering.Companion.natural
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

class RGBChannelGen(private val myBaseValues: List<Int>) {
    private var myRange: ClosedRange<Int>? = null

    init {
        val min = natural<Int>().min(myBaseValues)
        val max = natural<Int>().max(myBaseValues)
        for (r in RANGES) {
            if (r.contains(min) || r.contains(max)) {
                if (myRange == null) {
                    myRange = r
                } else {
                    myRange = myRange!!.span(r)
                }
            }
        }
    }

    /**
     * @param maxCount - count of new values required
     * @return List of generated value, size may be different than maxCount
     */
    fun generate(maxCount: Int): List<Int> {
        var genPerBaseValue = ceil(maxCount.toDouble() / myBaseValues.size).toInt()
        genPerBaseValue = min(maxValueCount() - 1, genPerBaseValue)

        var inc = floor(maxValueCount().toDouble() / (genPerBaseValue + 1)).toInt()
        // make increment a bit less regular
        inc = max(1, (inc * 1.33).toInt())

        val values = ArrayList<Int>(maxCount)
        var baseValues = myBaseValues
        while (values.size < maxCount) {
            val nextBaseValues = ArrayList<Int>()
            for (baseValue in baseValues) {
                var genValue = baseValue + inc
                if (!myRange!!.contains(genValue)) {
                    genValue = myRange!!.lowerEnd + (genValue - myRange!!.upperEnd)
                }
                values.add(genValue)
                nextBaseValues.add(genValue)
                if (values.size == maxCount) {
                    break
                }
            }

            baseValues = nextBaseValues
        }

        return values
    }

    private fun maxValueCount(): Int {
        return myRange!!.upperEnd - myRange!!.lowerEnd + 1
    }

    companion object {
        private val RANGES = arrayOf(
                ClosedRange(0, 37),
                ClosedRange(38, 97),
                ClosedRange(98, 157),
                ClosedRange(158, 217),
                ClosedRange(218, 255)
        )
    }
}
