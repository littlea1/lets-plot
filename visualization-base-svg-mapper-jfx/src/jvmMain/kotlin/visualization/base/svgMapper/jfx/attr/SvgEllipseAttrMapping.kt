package jetbrains.datalore.visualization.base.svgMapper.jfx.attr

import javafx.scene.shape.Ellipse
import jetbrains.datalore.visualization.base.svg.SvgEllipseElement

internal object SvgEllipseAttrMapping : SvgShapeMapping<Ellipse>() {
    override fun setAttribute(target: Ellipse, name: String, value: Any?) {
        when (name) {
            SvgEllipseElement.CX.name -> target.centerX = asDouble(value)
            SvgEllipseElement.CY.name -> target.centerY = asDouble(value)
            SvgEllipseElement.RX.name -> target.radiusX = asDouble(value)
            SvgEllipseElement.RY.name -> target.radiusY = asDouble(value)
            else -> super.setAttribute(target, name, value)
        }
    }
}