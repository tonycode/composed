plugins {
    alias(libs.plugins.convention.module.presentation)
}

dependencies {
    api(projects.demos.mbankApp.domain)

    implementation(projects.common.datetime.presentation)

    implementation(libs.mvikotlin)
    implementation(libs.mvikotlin.extensions.coroutines)
}
