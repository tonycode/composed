plugins {
    alias(libs.plugins.convention.module.ui)
}

android {
    namespace = "dev.tonycode.composed.common.designsystem.ui"
    resourcePrefix = "common_designsystem_ui_"
}

dependencies {
    api(projects.common.designsystem.presentation)
}
