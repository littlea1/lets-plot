package jetbrains.datalore.visualization.base.svgMapper.jfx

import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.image.ImageView
import javafx.scene.shape.Ellipse
import javafx.scene.shape.Rectangle
import javafx.scene.shape.SVGPath
import javafx.scene.text.Text
import jetbrains.datalore.visualization.base.svg.*
import jetbrains.datalore.visualization.base.svgMapper.jfx.attr.*

internal fun <TargetT : Node> createSvgAttrMapping(source: SvgElement, target: TargetT): SvgAttrMapping<TargetT> {
    return when (source) {
        is SvgSvgElement -> SvgSvgAttrMapping(target as Group) as SvgAttrMapping<TargetT>
        is SvgRectElement -> SvgRectAttrMapping(target as Rectangle) as SvgAttrMapping<TargetT>
        is SvgEllipseElement -> SvgEllipseAttrMapping(target as Ellipse) as SvgAttrMapping<TargetT>
        is SvgTextElement -> SvgTextElementAttrMapping(target as Text) as SvgAttrMapping<TargetT>
        is SvgPathElement -> SvgPathAttrMapping(target as SVGPath) as SvgAttrMapping<TargetT>
        is SvgImageElement -> SvgImageAttrMapping(target as ImageView) as SvgAttrMapping<TargetT>
//        is SvgStyleElement ->
        else -> throw IllegalArgumentException("Unsupported svg element: ${source.javaClass.simpleName}")
    }


}