package jetbrains.datalore.visualization.plot.gog.plot.presentation

import jetbrains.datalore.base.geometry.DoubleVector

import java.io.Serializable

class LabelMetrics : LabelSpec, Serializable {

    override val fontSize: Double
    override val isBold: Boolean
    override val isMonospaced: Boolean

    /**
     * for Serializable
     */
    constructor() {
        this.fontSize = 0.0;
        isBold = false
        isMonospaced = false
    }

    /**
     * @param fontSize in 'px' (same meaning as in CSS)
     */
    @JvmOverloads
    constructor(fontSize: Double, bold: Boolean = false, monospaced: Boolean = false) {
        this.fontSize = fontSize
        isBold = bold
        isMonospaced = monospaced
    }

    override fun dimensions(labelLength: Int): DoubleVector {
        return DoubleVector(width(labelLength), height())
    }

    override fun width(labelLength: Int): Double {
        var ratio = FONT_SIZE_TO_GLYPH_WIDTH_RATIO
        if (isMonospaced) {
            ratio = FONT_SIZE_TO_GLYPH_WIDTH_RATIO_MONOSPACED
        }

        val width = labelLength.toDouble() * fontSize * ratio + 2 * LABEL_PADDING
        return if (isBold) {
            width * FONT_WEIGHT_BOLD_TO_NORMAL_WIDTH_RATIO
        } else width
    }

    override fun height(): Double {
        return fontSize + 2 * LABEL_PADDING
    }

    companion object {
        private val FONT_SIZE_TO_GLYPH_WIDTH_RATIO = 0.67 //0.48; // 0.42;
        private val FONT_SIZE_TO_GLYPH_WIDTH_RATIO_MONOSPACED = 0.6
        private val FONT_WEIGHT_BOLD_TO_NORMAL_WIDTH_RATIO = 1.075
        private val LABEL_PADDING = 0.0 //2;
    }
}
