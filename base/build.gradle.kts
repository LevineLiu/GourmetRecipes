plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
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
        dataBinding = true
    }
}

dependencies {
    implementation(Dependencies.core_ktx)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.moshi)
    api(Dependencies.timber)
}