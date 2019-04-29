package jetbrains.datalore.visualization.plot.gog.plot.theme

interface AxisTheme {
    fun showLine(): Boolean

    fun showTickMarks(): Boolean

    fun showTickLabels(): Boolean

    fun showTitle(): Boolean

    fun lineWidth(): Double

    fun tickMarkWidth(): Double

    fun tickMarkLength(): Double

    fun tickMarkPadding(): Double

    fun tickLabelDistance(): Double {
        var result = tickMarkPadding()  // little space always
        if (showTickMarks()) {
            result += tickMarkLength()
        }
        return result
    }
}
