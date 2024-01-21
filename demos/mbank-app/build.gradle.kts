plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}


android {
    namespace = "dev.tonycode.composed.mbank"
    resourcePrefix = "mbank_"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        resourceConfigurations.addAll(listOf("en"))
        consumerProguardFile("consumer-rules.pro")
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

        isCoreLibraryDesugaringEnabled = true  // https://developer.android.com/studio/write/java8-support#library-desugaring
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
}

dependencies {
    //// Core
    implementation(platform(libs.kotlin.bom))  // Align versions of all Kotlin components
    implementation(libs.kotlin.stdlib.jdk8)  // Use the Kotlin standard library
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.hilt.android.base)
    kapt(libs.hilt.android.compiler)
    coreLibraryDesugaring(libs.desugar.jdkLibs)

    //// UI
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.constraintLayout.compose)

    implementation(projects.commonUi)

    implementation(libs.coil.compose)
    implementation(libs.valentinilk.compose.shimmer)

    //// Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
