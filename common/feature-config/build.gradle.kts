plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.common.utils)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)

                implementation(libs.decompose)
                implementation(libs.koin.core)
                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.coroutines)
            }
        }
    }
}