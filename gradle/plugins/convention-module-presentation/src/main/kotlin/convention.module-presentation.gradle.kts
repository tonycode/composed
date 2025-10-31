plugins {
    alias(libs.plugins.kotlin.jvm)
    id("convention.ktlint")
    jacoco
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    // Core
    implementation(platform(libs.kotlin.bom)) // Align versions of all Kotlin components
    implementation(libs.kotlin.stdlib)

    // UnitTests
    testImplementation(libs.junit)
    testImplementation(libs.google.truth)
}
