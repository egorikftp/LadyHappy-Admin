plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.cio)
                implementation(libs.ktor.core)
                implementation(libs.ktor.serialization)

                implementation(libs.kotlinx.serialization)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.kotlin.csv)
            }
        }
    }
}