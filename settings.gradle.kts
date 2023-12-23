rootProject.name = "composed"


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        // https://mvnrepository.com/ - find repository & artifact version for a given package

        google()  // https://maven.google.com/web/index.html - Android-specific artifacts
        mavenCentral()  // https://repo.maven.apache.org/maven2/
        maven("https://jitpack.io")  // for ArcProgressView

        //mavenLocal()  // ~/.m2/repository
    }
}

// https://docs.gradle.org/current/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


include(
    "common-ui",
    "demos:comida-app",
    "app",
)
