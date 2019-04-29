package jetbrains.datalore.visualization.plot.gog.plot.layout

internal abstract class PlotLayoutBase : PlotLayout {
    protected var paddingTop: Double = 0.toDouble()
        private set
    protected var paddingRight: Double = 0.toDouble()
        private set
    protected var paddingBottom: Double = 0.toDouble()
        private set
    protected var paddingLeft: Double = 0.toDouble()
        private set

    override fun setPadding(top: Double, right: Double, bottom: Double, left: Double) {
        paddingTop = top
        paddingRight = right
        paddingBottom = bottom
        paddingLeft = left
    }
}
