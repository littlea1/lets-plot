/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.vis.svgMapper.batik

import jetbrains.datalore.vis.svg.SvgNodeContainer
import jetbrains.datalore.vis.svg.SvgSvgElement
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SvgAwtPeerTest {
    private val root = SvgSvgElement()
    private val container: SvgNodeContainer =
        SvgNodeContainer(root)
    private val mapper: SvgRootDocumentMapper =
        SvgRootDocumentMapper(root)

    @Test
    fun documentInitNullPeer() {
        assertNull(container.getPeer())
    }

    @Test
    fun mapperAttachPeerSet() {
        mapper.attachRoot()
        assertNotNull(container.getPeer())
    }

    @Test
    fun mapperDetachRootNullPeer() {
        mapper.attachRoot()
        mapper.detachRoot()
        assertNull(container.getPeer())
    }
}
