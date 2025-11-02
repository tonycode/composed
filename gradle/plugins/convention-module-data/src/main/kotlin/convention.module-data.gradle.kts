plugins {
    alias(libs.plugins.kotlin.jvm)
    id("convention.ktlint")
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    // Core
    implementation(platform(libs.kotlin.bom)) // Align versions of all Kotlin components
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
}
