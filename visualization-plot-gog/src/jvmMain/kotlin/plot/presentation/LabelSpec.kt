package jetbrains.datalore.visualization.plot.gog.plot.presentation

import jetbrains.datalore.base.geometry.DoubleVector

interface LabelSpec {
    val isBold: Boolean

    val isMonospaced: Boolean

    val fontSize: Double

    fun dimensions(labelLength: Int): DoubleVector

    fun width(labelLength: Int): Double

    fun height(): Double
}
