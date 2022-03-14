plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.kotlin.coroutines.core)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.retrosheet)

                api(libs.moshi)
                implementation(libs.moshi.kotlin)

                api(libs.retrofit)
                implementation(libs.retrofit.moshi)
            }
        }

        jvmMain.dependsOn(commonMain)
    }
}
