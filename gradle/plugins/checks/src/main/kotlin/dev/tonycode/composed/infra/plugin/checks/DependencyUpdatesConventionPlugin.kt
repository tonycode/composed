package dev.tonycode.composed.infra.plugin.checks

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class DependencyUpdatesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply("com.github.ben-manes.versions")
        target.tasks.withType(DependencyUpdatesTask::class.java).configureEach {
            rejectVersionIf {
                candidate.version.isNonStable() && currentVersion.isStable()
            }

            checkForGradleUpdate = true
            outputFormatter = "html" // https://github.com/ben-manes/gradle-versions-plugin#report-format
            outputDir = "build/dependencyUpdates"
            reportfileName = "report"
        }
    }

    private fun String.isStable(): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { this.uppercase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        return (stableKeyword || regex.matches(this))
    }

    private fun String.isNonStable(): Boolean = isStable().not()
}
