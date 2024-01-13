plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    jacoco
}


android {
    namespace = "dev.tonycode.composed.common.ui"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        resourceConfigurations.addAll(listOf("en"))
        consumerProguardFile("consumer-rules.pro")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileSdk = libs.versions.compileSdk.get().toInt()
    buildToolsVersion = libs.versions.buildTools.get()

    sourceSets {
        all {
            kotlin.srcDir("src/$name/kotlin")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerExtension.get()
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            enableUnitTestCoverage = true
            //enableAndroidTestCoverage = true
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    testCoverage {
        jacocoVersion = libs.versions.jacoco.get()
    }
}

dependencies {
    //// Core
    implementation(platform(libs.kotlin.bom))  // Align versions of all Kotlin components
    implementation(libs.kotlin.stdlib.jdk8)  // Use the Kotlin standard library
    implementation(libs.androidx.core.ktx)

    //// UI
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //// UnitTests
    testImplementation(libs.junit)
    testImplementation(libs.google.truth)
}
