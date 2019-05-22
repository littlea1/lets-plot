package jetbrains.datalore.visualization.plot.builder.event3

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.plot.base.event3.GeomTarget
import jetbrains.datalore.visualization.plot.base.event3.GeomTargetLocator.LookupStrategy
import jetbrains.datalore.visualization.plot.builder.event3.TestUtil.HitIndex
import jetbrains.datalore.visualization.plot.builder.event3.TestUtil.PathPoint
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class GeomTargetLocatorImplPathHoverXTest : GeomTargetLocatorPathXTestBase() {

    override val strategy: LookupStrategy
        get() = LookupStrategy.HOVER

    @Test
    fun hoverX_WhenCloserToLeft() {
        assertThat(
                findTargets(rightFrom(p1, THIS_POINT_DISTANCE))
        ).first().has(HitIndex.equalTo(p1.hitIndex))
    }


    @Test
    fun hoverX_WhenCloserToRight() {
        assertThat(
                findTargets(rightFrom(p1, NEXT_POINT_DISTANCE))
        ).first().has(HitIndex.equalTo(p2.hitIndex))
    }

    @Test
    fun hoverX_WhenInTheMiddle_ShouldSelectSecondPoint() {
        assertThat(
                findTargets(rightFrom(p1, MIDDLE_POINTS_DISTANCE))
        ).first().has(HitIndex.equalTo(p1.hitIndex))
    }


    @Test
    fun hoverX_WhenOutOfPath_ShouldFindNothing() {
        assertThat(
                findTargets(leftFrom(p0, NEXT_POINT_DISTANCE))
        ).isEmpty()
    }


    private fun leftFrom(p: PathPoint, distance: Double): DoubleVector {
        return DoubleVector(p.x - distance, p.y)
    }

    private fun rightFrom(p: PathPoint, distance: Double): DoubleVector {
        return DoubleVector(p.x + distance, p.y)
    }


    private fun findTargets(p: DoubleVector): List<GeomTarget> {
        return TestUtil.findTargets(locator, p)
    }
}