plugins {
    alias(libs.plugins.convention.module.ui)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "dev.tonycode.composed.mbank"
    resourcePrefix = "mbank_"
}

dependencies {
    implementation(projects.demos.mbankApp.data) // for preview stubs
    implementation(projects.demos.mbankApp.presentation)

    implementation(projects.common.currency.presentation)
    implementation(projects.common.datetime.presentation)
    implementation(projects.common.designsystem.presentation)
    implementation(projects.common.designsystem.ui)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.mvikotlin)
    implementation(libs.mvikotlin.main)
    implementation(libs.mvikotlin.logging)
    implementation(libs.essenty)

    // UI
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.constraintLayout.compose)

    implementation(libs.coil.compose)
    implementation(libs.valentinilk.compose.shimmer)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
}
