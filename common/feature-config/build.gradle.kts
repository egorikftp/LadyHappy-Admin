plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.common.network)
                implementation(projects.common.utils)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)

                implementation(libs.decompose)
                implementation(libs.koin.core)
                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.coroutines)

                implementation(libs.ktor.cio)
                implementation(libs.ktor.core)
                implementation(libs.ktor.serialization)

                implementation(libs.kotlinx.serialization)
            }
        }

        val jvmMain by getting
    }
}