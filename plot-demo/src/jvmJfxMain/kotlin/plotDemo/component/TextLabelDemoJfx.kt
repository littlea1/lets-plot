/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.component

import jetbrains.datalore.plotDemo.model.component.TextLabelDemo
import jetbrains.datalore.vis.demoUtils.SceneMapperDemoFrame

fun main() {
    with(TextLabelDemo()) {
        val demoModels = listOf(createModel())
        val svgRoots = createSvgRoots(demoModels)
        SceneMapperDemoFrame.showSvg(
            svgRoots,
            listOf("/text-label-demo.css"),
            demoComponentSize,
            "Text label anchor and rotation"
        )
    }
}

