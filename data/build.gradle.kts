plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.1.0-rc01"
}

kotlin {
    jvm()

    sourceSets {
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        val commonMain by getting {
            dependencies {
                api(compose.ui)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(libs.koin.core)

                implementation(libs.kotlin.coroutines.core)
                implementation(libs.voyager.navigator)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(libs.koin.core)
                api("com.github.theapache64:retrosheet:2.0.0-beta03")

                val moshiVersion = "1.13.0"
                api("com.squareup.moshi:moshi:$moshiVersion")
                implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")

                val retrofitVersion = "2.9.0"
                api("com.squareup.retrofit2:retrofit:$retrofitVersion")
                implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
            }
        }
    }
}
