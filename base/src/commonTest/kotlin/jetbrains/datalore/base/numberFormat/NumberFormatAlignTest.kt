package jetbrains.datalore.base.numberFormat

import kotlin.test.Test
import kotlin.test.assertEquals

class NumberFormatAlignTest {
    @Test
    fun alignLeft() {
        assertEquals("0", NumberFormat("<1,d").apply(0))
        assertEquals("0 ", NumberFormat("<2,d").apply(0))
        assertEquals("0  ", NumberFormat("<3,d").apply(0))
        assertEquals("0    ", NumberFormat("<5,d").apply(0))
        assertEquals("0       ", NumberFormat("<8,d").apply(0))
        assertEquals("0            ", NumberFormat("<13,d").apply(0))
        assertEquals("0                    ", NumberFormat("<21,d").apply(0))
    }

    @Test
    fun alignRight() {
        assertEquals("0", NumberFormat(">1,d").apply(0))
        assertEquals(" 0", NumberFormat(">2,d").apply(0))
        assertEquals("  0", NumberFormat(">3,d").apply(0))
        assertEquals("    0", NumberFormat(">5,d").apply(0))
        assertEquals("       0", NumberFormat(">8,d").apply(0))
        assertEquals("            0", NumberFormat(">13,d").apply(0))
        assertEquals("                    0", NumberFormat(">21,d").apply(0))
        assertEquals("                1,000", NumberFormat(">21,d").apply(1000))
        assertEquals("                1e+21", NumberFormat(">21,d").apply(1e21))
    }

    @Test
    fun alignCenter() {
        assertEquals("0", NumberFormat("^1,d").apply(0))
        assertEquals("0 ", NumberFormat("^2,d").apply(0))
        assertEquals(" 0 ", NumberFormat("^3,d").apply(0))
        assertEquals("  0  ", NumberFormat("^5,d").apply(0))
        assertEquals("   0    ", NumberFormat("^8,d").apply(0))
        assertEquals("      0      ", NumberFormat("^13,d").apply(0))
        assertEquals("          0          ", NumberFormat("^21,d").apply(0))
        assertEquals("        1,000        ", NumberFormat("^21,d").apply(1000))
        assertEquals("        1e+21        ", NumberFormat("^21,d").apply(1e21))
    }
}