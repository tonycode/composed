plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

dependencies {
    implementation(libs.ktlint.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("ktlint") {
            id = "convention.ktlint"
            implementationClass = "dev.tonycode.composed.infra.plugin.checks.KtLintPlugin"
        }
    }
}
