pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Ladyhappy-Admin"

include(":android")
include(":desktop")

include(":common:network")
include(":common:theme")
include(":common:utils")

include(":common:feature-root")

include(":common:feature-main")
include(":common:feature-config")
include(":common:feature-features")
