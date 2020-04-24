pluginManagement {
    plugins {
            val kotlin_version: String by settings
            val idea_ext_version: String by settings
        kotlin("multiplatform") version kotlin_version
        id("org.jetbrains.kotlin.js") version kotlin_version
        id("org.jetbrains.gradle.plugin.idea-ext") version idea_ext_version
        id("com.jfrog.bintray") version "1.8.4"
        id("com.github.johnrengelman.shadow") version "5.1.0"
//    id("com.moowork.node") version "1.3.1"
        //classpath "org.jetbrains.kotlin:kotlin-serialization:$kotlin_version"
    }
}

rootProject.name = "lets-plot"

include(
        "base-portable",
        "base",
        "gis",
        "livemap",
        "livemap-demo",
        "livemap-jfx-package",
        "plot-livemap",
        "plot-livemap-stub",
        "mapper-core",
        "vis-svg-portable",
        "vis-svg-mapper",
        "vis-svg-mapper-jfx",
        "vis-svg-mapper-batik",
        "vis-canvas",
        "plot-base",
        "plot-base-portable",
        "plot-common-portable",
        "plot-common",
        "plot-builder-portable",
        "plot-builder",
        "plot-config-portable",
        "plot-config",
        "vis-demo-common",
        "vis-demo-common-jfx",
        "vis-demo-common-batik",
        "vis-demo-svg-mapper",
        "plot-demo",
        "plot-demo-common",
        "python-extension",
        "python-package-build",
        "js-package",
        "js-package:js-publish-latest",
        "js-package:js-publish-version",
        "jvm-package:jvm-publish-common",
        "jvm-package:jvm-publish-jfx",
        "jvm-package:jvm-publish-batik",
        "test-common"
)



/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

// publishing
enableFeaturePreview("GRADLE_METADATA")