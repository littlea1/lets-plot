package jetbrains.datalore.visualization.plot.gog.plot

class LegendOptions : GuideOptions() {

    private var myColCount: Int? = null
    private var myRowCount: Int? = null
    var isByRow: Boolean = false

    var colCount: Int
        get() = myColCount!!
        set(colCount) {
            myColCount = Math.max(1, colCount)
        }

    var rowCount: Int
        get() = myRowCount!!
        set(rowCount) {
            myRowCount = Math.max(1, rowCount)
        }

    fun hasColCount(): Boolean {
        return myColCount != null
    }

    fun hasRowCount(): Boolean {
        return myRowCount != null
    }

    companion object {
        fun combine(optionsList: List<LegendOptions>): LegendOptions {
            val result = LegendOptions()
            for (options in optionsList) {
                if (options.isByRow) {
                    result.isByRow = true
                }
                if (options.hasColCount()) {
                    result.colCount = options.colCount
                }
                if (options.hasRowCount()) {
                    result.rowCount = options.rowCount
                }
            }
            return result
        }
    }
}
