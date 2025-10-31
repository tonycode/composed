plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    id("convention.ktlint")
}

android {
    namespace = "dev.tonycode.composed.comida"
    resourcePrefix = "comida_"

    defaultConfig {
        minSdk =
            libs.versions.minSdk
                .get()
                .toInt()
        resourceConfigurations.addAll(listOf("en"))
        consumerProguardFile("consumer-rules.pro")
    }

    compileSdk =
        libs.versions.compileSdk
            .get()
            .toInt()
    buildToolsVersion = libs.versions.buildTools.get()

    sourceSets {
        all {
            kotlin.srcDir("src/$name/kotlin")
        }
    }

    val javaVersionString = libs.versions.java.get()
    val javaVersion = JavaVersion.toVersion(javaVersionString.toInt())
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = javaVersionString
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core
    implementation(platform(libs.kotlin.bom)) // Align versions of all Kotlin components
    implementation(libs.kotlin.stdlib) // Use the Kotlin standard library
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // UI
    implementation(projects.common.designsystem.ui)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.constraintLayout.compose)

    implementation(libs.coil.compose)
    implementation(libs.valentinilk.compose.shimmer)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
}
