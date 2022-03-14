plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.compose")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    /* sourceSets {
         named("main") {
             manifest.srcFile("src/androidMain/AndroidManifest.xml")
             res.srcDirs("src/androidMain/res")
         }
     }*/
}

kotlin {
    jvm()
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)

                implementation(libs.decompose)
                implementation(libs.decompose.extensions)
                implementation(libs.koin.core)

                implementation(projects.common.network)
                implementation(projects.common.viewmodel)
            }
        }
    }
}