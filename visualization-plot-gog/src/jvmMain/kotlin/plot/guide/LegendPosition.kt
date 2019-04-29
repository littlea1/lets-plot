package jetbrains.datalore.visualization.plot.gog.plot.guide

/**
 * the position of legends ("none", "left", "right", "bottom", "top", or two-element numeric vector)
 */
class LegendPosition(val x: Double, val y: Double) {

    val isFixed: Boolean
        get() = this === LEFT || this === RIGHT ||
                this === TOP || this === BOTTOM

    val isHidden: Boolean
        get() = this === NONE

    val isOverlay: Boolean
        get() = !(isFixed || isHidden)

    companion object {
        val RIGHT = LegendPosition(1.0, 0.5)
        val LEFT = LegendPosition(0.0, 0.5)
        val TOP = LegendPosition(0.5, 1.0)
        val BOTTOM = LegendPosition(0.5, 1.0)
        val NONE = LegendPosition(java.lang.Double.NaN, java.lang.Double.NaN)
    }
}
