/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.plotAssembler

import jetbrains.datalore.plotDemo.model.plotAssembler.BarPlotDemo
import jetbrains.datalore.vis.demoUtils.BatikMapperDemoFrame

class BarPlotDemoBatik : BarPlotDemo() {

    private fun show() {
        val plots = createPlots()
        val svgRoots = createSvgRootsFromPlots(plots)
        BatikMapperDemoFrame.showSvg(svgRoots, demoComponentSize, "Bar plot")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            BarPlotDemoBatik().show()
        }
    }
}
