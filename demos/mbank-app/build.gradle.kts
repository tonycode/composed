plugins {
    alias(libs.plugins.convention.module.ui)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("convention.ktlint")
}


android {
    namespace = "dev.tonycode.composed.mbank"
    resourcePrefix = "mbank_"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // UI
    implementation(projects.common.currency.presentation)
    implementation(projects.common.datetime.presentation)
    implementation(projects.common.designsystem.presentation)
    implementation(projects.common.designsystem.ui)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.constraintLayout.compose)

    implementation(libs.coil.compose)
    implementation(libs.valentinilk.compose.shimmer)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
}
