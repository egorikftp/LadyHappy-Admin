plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
}

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.network)
                implementation(projects.common.utils)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)

                implementation(libs.decompose)

                implementation(libs.koin.core)

                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.coroutines.swing)

                implementation(libs.kotlinx.serialization)

                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.serialization.json)

                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.coroutines)
            }
        }
    }
}