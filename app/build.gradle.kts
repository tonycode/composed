import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)

    alias(libs.plugins.grgit)
}


android {
    namespace = "dev.tonycode.composed"

    defaultConfig {
        applicationId = "dev.tonycode.composed"
        versionCode = 1
        versionName = "1.0.0"
        archivesName = "composed-v$versionName-build_$versionCode"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        resourceConfigurations.addAll(listOf("en"))

        // build info
        buildConfigField("String", "APP_NAME", "\"${ rootProject.name }\"")
        buildConfigField("String", "GIT_BRANCH_NAME", "\"${ grgit.branch.current().name }\"")
        buildConfigField("String", "GIT_COMMIT_ID", "\"${ grgit.head().abbreviatedId }\"")
        buildConfigField("String", "BUILD_TIME", "\"${
            SimpleDateFormat("yyyy.MM.dd EEE 'at' HH:mm:ss.SSS z").apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }.format(Date(System.currentTimeMillis()))
        }\"")
        buildConfigField("long", "BUILD_TIME_MILLIS", "${ System.currentTimeMillis() }L")
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
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerExtension.get()
    }


    signingConfigs {
        create("release") {
            storeFile = file("../keystore/demo.jks")
            storePassword = "demo12345"
            keyAlias = "key0"
            keyPassword = "demo12345"
        }
    }

    buildTypes {
        debug {
            resValue("string", "app_name", "Composed dbg")
            applicationIdSuffix = ".debug"

            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }

        release {
            resValue("string", "app_name", "Composed")
            signingConfig = signingConfigs.getByName("release")

            isDebuggable = false
            // https://developer.android.com/studio/build/shrink-code
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    //// Core
    implementation(platform(libs.kotlin.bom))  // Align versions of all Kotlin components
    implementation(libs.kotlin.stdlib.jdk8)  // Use the Kotlin standard library
    implementation(libs.androidx.core.ktx)
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

    implementation(projects.commonUi)

    //// Demos
    implementation(projects.demos.comidaApp)
    implementation(projects.demos.mbankApp)

    //// Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
