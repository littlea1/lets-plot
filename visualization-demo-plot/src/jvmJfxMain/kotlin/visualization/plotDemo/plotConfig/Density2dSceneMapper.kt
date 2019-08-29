package jetbrains.datalore.visualization.plotDemo.plotConfig

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.demoUtils.jfx.SceneMapperDemoFactory
import jetbrains.datalore.visualization.plot.builder.presentation.Style.JFX_PLOT_STYLESHEET
import jetbrains.datalore.visualization.plotDemo.model.plotConfig.Density2d

object Density2dSceneMapper {
    @JvmStatic
    fun main(args: Array<String>) {
        with(Density2d()) {
            @Suppress("UNCHECKED_CAST")
            val plotSpecList = plotSpecList() as List<MutableMap<String, Any>>
            PlotConfigDemoUtil.show(
                "Density2d plot",
                plotSpecList,
                SceneMapperDemoFactory(JFX_PLOT_STYLESHEET),
                DoubleVector(600.0, 600.0)
            )
        }
    }
}