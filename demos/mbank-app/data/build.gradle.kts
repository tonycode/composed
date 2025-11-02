plugins {
    alias(libs.plugins.convention.module.data)
}

dependencies {
    implementation(projects.demos.mbankApp.domain)

    implementation(projects.common.currency.domain)
    implementation(projects.common.datetime.domain)
}
