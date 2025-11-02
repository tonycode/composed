plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

dependencies {
    implementation(libs.dependencyUpdates.gradlePlugin)
    implementation(libs.ktlint.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("dependency-updates") {
            id = "convention.dependency-updates"
            implementationClass = "dev.tonycode.composed.infra.plugin.checks.DependencyUpdatesConventionPlugin"
        }

        create("ktlint") {
            id = "convention.ktlint"
            implementationClass = "dev.tonycode.composed.infra.plugin.checks.KtLintConventionPlugin"
        }
    }
}
