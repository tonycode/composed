package dev.tonycode.composed.infra.plugin.checks

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class KtLintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")

            target.extensions.configure<KtlintExtension> {
                version.set("1.7.1")
                android.set(true)
                enableExperimentalRules.set(true)
                filter {
                    exclude("**/generated/**")
                }
            }
        }
    }
}
