package jetbrains.datalore.visualization.plot.gog.plot.assemble

import jetbrains.datalore.visualization.plot.gog.core.data.DataFrame
import jetbrains.datalore.visualization.plot.gog.core.data.stat.Stats
import jetbrains.datalore.visualization.plot.gog.core.render.Aes
import jetbrains.datalore.visualization.plot.gog.core.scale.Scales
import jetbrains.datalore.visualization.plot.gog.plot.VarBinding
import jetbrains.datalore.visualization.plot.gog.plot.assemble.geom.GeomProvider
import jetbrains.datalore.visualization.plot.gog.plot.scale.ScaleProviderHelper
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GeomLayerBuilderTest {
    private fun checkBoundDataSize(data: DataFrame, binding: VarBinding, size: Int) {
        assertTrue(data.has(binding.`var`), "has " + binding.`var`)
        assertEquals(size.toLong(), data[binding.`var`].size.toLong(), "size " + binding.`var`)
    }

    private fun checkNotOriginalVar(data: DataFrame, binding: VarBinding) {
        assertTrue(data.has(binding.`var`), "has " + binding.`var`)
        assertFalse(binding.`var`.isOrigin, "not original " + binding.`var`)
    }


    @Test
    fun buildHistogram() {
        /*
        x=[0,1,0,1]
        cat = ['a','a','b','b']
        data = dict(x=x,cat=cat)
        ggplot(data) + geom_histogram(aes(x='x',fill='cat'))
        */
        val X = DataFrame.Variable("x")
        val CAT = DataFrame.Variable("cat")
        val data = DataFrame.Builder()
                .put(X, listOf(0.0, 1.0, 0.0, 1.0))
                .put(CAT, listOf("a", "a", "b", "b"))
                .build()

        val geomProvider = GeomProvider.histogram()
        val stat = Stats.bin().build()
        val posProvider = PosProvider.barStack()

        val bindings = ArrayList<VarBinding>()
        bindings.add(VarBinding(X, Aes.X, Scales.continuousDomain("x", Aes.X)))
        bindings.add(VarBinding(CAT, Aes.FILL, ScaleProviderHelper.createDefault(Aes.FILL).createScale(data, CAT)))

        val histogramLayer = GeomLayerBuilder.demoAndTest()
                .stat(stat)
                .geom(geomProvider)
                .pos(posProvider)
//                .addConstantAes(Aes.ALPHA, 0.5)
                .addBinding(bindings[0])
                .addBinding(bindings[1])
                .build(data)


        assertTrue(histogramLayer.hasBinding(Aes.X))
        assertTrue(histogramLayer.hasBinding(Aes.Y))
        assertTrue(histogramLayer.hasBinding(Aes.FILL))

        val layerData = histogramLayer.dataFrame

        checkBoundDataSize(layerData, histogramLayer.getBinding(Aes.X), 60)
        checkBoundDataSize(layerData, histogramLayer.getBinding(Aes.Y), 60)
        checkBoundDataSize(layerData, histogramLayer.getBinding(Aes.FILL), 60)

        checkNotOriginalVar(layerData, histogramLayer.getBinding(Aes.X))
        checkNotOriginalVar(layerData, histogramLayer.getBinding(Aes.Y))
        checkNotOriginalVar(layerData, histogramLayer.getBinding(Aes.FILL))
    }
}