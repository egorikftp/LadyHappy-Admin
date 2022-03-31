plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.common.retrosheetKmm)

                implementation(libs.koin.core)

                implementation(libs.ktor.core)
                implementation(libs.ktor.cio)
                implementation(libs.ktor.serialization)
            }
        }

        val jvmMain by getting
    }
}
