plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.compose")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}

kotlin {
    jvm()
    android()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.imageloader)
                implementation(projects.common.network)
                implementation(projects.common.utils)

                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.coroutines.swing)

                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)

                implementation(libs.decompose)

                implementation(libs.koin.core)

                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.serialization.json)

                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.coroutines)
            }
        }
    }
}