package jetbrains.datalore.visualization.plot.gog.plot.theme

import jetbrains.datalore.visualization.plot.gog.plot.presentation.Defaults.Plot

class DefaultAxisTheme : AxisTheme {
    override fun showLine(): Boolean {
        return true
    }

    override fun showTickMarks(): Boolean {
        return true
    }

    override fun showTickLabels(): Boolean {
        return true
    }

    override fun showTitle(): Boolean {
        return true
    }

    override fun lineWidth(): Double {
        return Plot.Axis.LINE_WIDTH
    }

    override fun tickMarkWidth(): Double {
        return Plot.Axis.TICK_LINE_WIDTH
    }

    override fun tickMarkLength(): Double {
        return 6.0
    }

    override fun tickMarkPadding(): Double {
        return 3.0
    }
}
