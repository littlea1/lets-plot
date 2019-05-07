package jetbrains.datalore.visualization.plot.gog.server.config.transform

import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.DATA_COLUMN_JOIN_KEY
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.MAP_COLUMN_GEOJSON
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.MAP_COLUMN_JOIN_KEY
import jetbrains.datalore.visualization.plot.gog.config.Option.Geom.Choropleth.GEO_POSITIONS
import jetbrains.datalore.visualization.plot.gog.config.Option.Layer.DATA
import jetbrains.datalore.visualization.plot.gog.config.Option.Layer.MAPPING
import jetbrains.datalore.visualization.plot.gog.config.Option.Meta.DATA_META
import jetbrains.datalore.visualization.plot.gog.config.Option.Meta.GeoDataFrame
import jetbrains.datalore.visualization.plot.gog.config.Option.Plot
import jetbrains.datalore.visualization.plot.gog.config.transform.SpecChange
import jetbrains.datalore.visualization.plot.gog.config.transform.SpecChangeContext
import jetbrains.datalore.visualization.plot.gog.config.transform.SpecSelector
import jetbrains.datalore.visualization.plot.gog.core.render.Aes.Companion.MAP_ID

class GeoDataFrameMappingChange : SpecChange {

    override fun apply(spec: MutableMap<String, Any>, ctx: SpecChangeContext) {
        val dataSpec = spec[DATA] as MutableMap<String, Any>
        val geometryColumn = ((spec[DATA_META] as Map<*, *>)[GeoDataFrame.TAG] as Map<*, *>)[GeoDataFrame.GEOMETRY] as String

        val keys = generateKeys((dataSpec[geometryColumn] as List<*>).size)

        spec[GEO_POSITIONS] = HashMap(
                mapOf(
                        MAP_COLUMN_JOIN_KEY to keys,
                        MAP_COLUMN_GEOJSON to dataSpec[geometryColumn]
                )
        )

        dataSpec.remove(geometryColumn)
        dataSpec[DATA_COLUMN_JOIN_KEY] = keys

        spec.computeIfAbsent(MAPPING) { HashMap<Any, Any>() }

        val mapping = spec[MAPPING] as MutableMap<String, Any>
        mapping[MAP_ID.name()] = DATA_COLUMN_JOIN_KEY
    }

    override fun isApplicable(spec: Map<String, Any>): Boolean {
        return (spec[GEO_POSITIONS] == null
                && spec[DATA_META] is Map<*, *>
                && (spec[DATA_META] as Map<*, *>).containsKey(GeoDataFrame.TAG))
    }

    companion object {
        internal fun specSelector(): SpecSelector {
            return SpecSelector.of(Plot.LAYERS)
        }

        private fun generateKeys(size: Int): List<String> {
            return (0 until size).map { it.toString() }
        }
    }
}