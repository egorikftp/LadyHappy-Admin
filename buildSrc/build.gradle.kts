plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(libs.plugin.android.gradle)
    implementation(libs.plugin.kotlin.gradle)
}