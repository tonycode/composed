rootProject.name = "composed"

pluginManagement {
    repositories {
        gradlePluginPortal() // https://plugins.gradle.org
        google()
        mavenCentral()
    }
    includeBuild("gradle/plugins")
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS

    repositories {
        // https://mvnrepository.com/ - find repository & artifact version for a given package

        google()  // https://maven.google.com/web/index.html - Android-specific artifacts
        mavenCentral()  // https://repo.maven.apache.org/maven2/
        //maven("https://jitpack.io")

        //mavenLocal()  // ~/.m2/repository
    }
}

// https://docs.gradle.org/current/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("app")
include("common:currency:domain")
include("common:currency:presentation")
include("common:datetime:domain")
include("common:datetime:presentation")
include("common:designsystem:presentation")
include("common:designsystem:ui")
include("common:strings:presentation")
include("demos:comida-app")
include("demos:mbank-app")
