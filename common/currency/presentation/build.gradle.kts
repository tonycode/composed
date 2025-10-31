plugins {
    alias(libs.plugins.convention.module.presentation)
}

dependencies {
    api(projects.common.currency.domain)
    implementation(projects.common.strings.presentation)
}
