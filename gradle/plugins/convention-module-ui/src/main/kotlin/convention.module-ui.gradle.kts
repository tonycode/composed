plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

android {
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        resourceConfigurations.addAll(listOf("en"))
        consumerProguardFile("consumer-rules.pro")

        // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileSdk = libs.versions.compileSdk.get().toInt()
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

        isCoreLibraryDesugaringEnabled = true  // https://developer.android.com/studio/write/java8-support#library-desugaring
    }
    kotlinOptions {
        jvmTarget = javaVersionString
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            enableUnitTestCoverage = true
            //enableAndroidTestCoverage = true
        }
    }

    // testOptions {
    //     unitTests {
    //         isIncludeAndroidResources = true
    //         isReturnDefaultValues = true
    //     }
    // }
    // testCoverage {
    //     jacocoVersion = libs.versions.jacoco.get()
    // }
}

dependencies {
    // Core
    implementation(platform(libs.kotlin.bom)) // Align versions of all Kotlin components
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)
    coreLibraryDesugaring(libs.desugar.jdkLibs)

    // UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
}
