import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.egoriku.ladyhappy"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.common.featureRoot)
                implementation(projects.common.theme)
                implementation(projects.common.utils)

                implementation(compose.desktop.currentOs)

                implementation(libs.decompose)
                implementation(libs.decompose.extensions)
                implementation(libs.koin.core)
                implementation(libs.themedetector)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Ladyhappy Admin Tools"
            packageVersion = "1.0.0"
        }
    }
}