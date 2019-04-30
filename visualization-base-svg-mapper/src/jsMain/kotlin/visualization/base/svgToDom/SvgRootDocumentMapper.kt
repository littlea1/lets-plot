package jetbrains.datalore.visualization.base.svgToDom

import jetbrains.datalore.mapper.core.Mapper
import jetbrains.datalore.mapper.core.MappingContext
import jetbrains.datalore.visualization.base.svg.SvgSvgElement
import org.w3c.dom.svg.SVGSVGElement
import kotlin.browser.document

class SvgRootDocumentMapper(source: SvgSvgElement): Mapper<SvgSvgElement, SVGSVGElement>(source, createDocument()) {

    companion object {
        private const val SVG_NAMESPACE_URI = "http://www.w3.org/2000/svg"

        private fun createDocument(): SVGSVGElement {
            return document.createElementNS(SVG_NAMESPACE_URI, "svg") as SVGSVGElement
        }
    }

    private var myRootMapper: SvgElementMapper<SvgSvgElement, SVGSVGElement>? = null

    override fun onAttach(ctx: MappingContext) {
        super.onAttach(ctx)

        if (!source.isAttached()) {
            throw IllegalStateException("Element must be attached")
        }

        val peer = SvgDomPeer()
        source.container().setPeer(peer)

        myRootMapper = SvgElementMapper(source, target, peer)
        target.setAttribute("shape-rendering", "geometricPrecision")
        myRootMapper!!.attachRoot()
    }

    override fun onDetach() {
        myRootMapper!!.detachRoot()
        myRootMapper = null

        if (source.isAttached()) {
            source.container().setPeer(null)
        }

        super.onDetach()
    }
}