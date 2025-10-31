rootProject.name = "plugins"

plugins {
    id("dev.panuszewski.typesafe-conventions") version "0.9.1" // https://github.com/radoslaw-panuszewski/typesafe-conventions-gradle-plugin
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        gradlePluginPortal() // https://plugins.gradle.org
        google() // https://maven.google.com/web/index.html - Android-specific artifacts
    }

    versionCatalogs {
        create("libs") {
            from(files("../libs.versions.toml"))
        }
    }
}

include(":convention-module-data")
include(":convention-module-domain")
include(":convention-module-presentation")
include(":convention-module-ui")
