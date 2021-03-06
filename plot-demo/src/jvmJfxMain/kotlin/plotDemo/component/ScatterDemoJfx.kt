/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.component

import jetbrains.datalore.plot.builder.presentation.Style
import jetbrains.datalore.plotDemo.model.component.ScatterDemo
import jetbrains.datalore.vis.demoUtils.SceneMapperDemoFrame

class ScatterDemoJfx : ScatterDemo() {

    private fun show() {
        val demoModels = createModels()
        val svgRoots = createSvgRoots(demoModels)
        SceneMapperDemoFrame.showSvg(svgRoots, listOf(Style.JFX_PLOT_STYLESHEET), demoComponentSize, "Point geom with scale breaks and limits")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ScatterDemoJfx().show()
        }
    }
}
