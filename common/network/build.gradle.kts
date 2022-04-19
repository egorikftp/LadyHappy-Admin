plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                api(projects.common.retrosheetKmm)

                implementation(libs.koin.core)

                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.serialization.json)
            }
        }
    }
}
