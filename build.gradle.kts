import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply(plugin = "com.github.ben-manes.versions")

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.plugin.android.gradle)
        classpath(libs.plugin.compose.gradle)
        classpath(libs.plugin.kotlin.gradle)
        classpath(libs.plugin.kotlin.serialization.gradle)
        classpath(libs.plugin.versions.gradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
    }
}

tasks {
    registering(Delete::class) {
        delete(buildDir)
    }

    named<DependencyUpdatesTask>("dependencyUpdates") {
        resolutionStrategy {
            componentSelection {
                all {
                    val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea", "m1")
                        .any { qualifier ->
                            candidate.version.matches(Regex("(?i).*[.-]$qualifier[.\\d-+]*"))
                        }
                    if (rejected) {
                        reject("Release candidate")
                    }
                }
            }
        }
    }
}