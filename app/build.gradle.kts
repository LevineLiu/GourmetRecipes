plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.constraintlayout)
    implementation(Dependencies.lifecycle_livedata)
    implementation(Dependencies.lifecycle_viewmodel)
    implementation(Dependencies.navigation_fragment)
    implementation(Dependencies.navigation_ui)
    implementation(Dependencies.viewpager2)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_convert_moshi)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttp_logging_interceptor)
    implementation(Dependencies.glide)
    kapt(Dependencies.glide_compiler)
    implementation(Dependencies.glide_okhttp_integration)
    implementation(Dependencies.glide_transformations)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.ext_junit)
    androidTestImplementation(Dependencies.espresso_core)

    api(project(":base"))
}