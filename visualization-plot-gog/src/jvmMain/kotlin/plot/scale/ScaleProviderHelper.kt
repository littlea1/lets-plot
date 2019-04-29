package jetbrains.datalore.visualization.plot.gog.plot.scale

import jetbrains.datalore.visualization.plot.gog.core.render.Aes
import jetbrains.datalore.visualization.plot.gog.plot.assemble.TypedScaleProviderMap

object ScaleProviderHelper {
    fun getOrCreateDefault(aes: Aes<*>, providers: TypedScaleProviderMap): ScaleProvider<*> {
        var realAes = aes
        if (Aes.isPositionalX(aes)) {
            realAes = Aes.X
        } else if (Aes.isPositionalY(aes)) {
            realAes = Aes.Y
        }
        return if (providers.containsKey(realAes)) {
            providers.get(realAes)
        } else createDefault(realAes)
    }

    fun <T> createDefault(aes: Aes<T>): ScaleProvider<T> {
        return ScaleProviderBuilder(aes).build()
    }

    fun <T> createDefault(aes: Aes<T>, name: String): ScaleProvider<T> {
        return ScaleProviderBuilder(aes)
                .name(name)
                .build()
    }

    fun <T> create(name: String, aes: Aes<T>, mapperProvider: MapperProvider<T>): ScaleProvider<T> {
        return ScaleProviderBuilder(aes)
                .mapperProvider(mapperProvider)
                .name(name)
                .build()
    }
}
