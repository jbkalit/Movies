plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Version.compile_sdk
    buildToolsVersion = Version.build_tools

    defaultConfig {
        applicationId = "com.jbkalit.movies"
        minSdk = Version.min_sdk
        targetSdk = Version.target_sdk
        versionCode = Version.version_code
        versionName = Version.version_name

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isShrinkResources = false
            isMinifyEnabled = false
            isDebuggable = true
        }

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

kapt {
    generateStubs = true
}

dependencies {

    // modules
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependency.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.APPCOMPAT)
    implementation(Dependency.MATERIAL)
    implementation(Dependency.CONSTRAINT_LAYOUT)
    implementation(Dependency.LEGACY_SUPPORT)
    implementation(Dependency.VIEWMODEL)
    implementation(Dependency.LIVEDATA)
    implementation(Dependency.FRAGMENT_KTX)
    implementation(Dependency.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependency.NAVIGATION_UI)

    // glide
    implementation(Dependency.Glide.GLIDE)
    kapt(Dependency.Glide.GLIDE_COMPILER)

    // okhttp
    implementation(Dependency.Network.OK_HTTP_INTERCEPTOR)

    // retrofit
    implementation(Dependency.Network.RETROFIT)
    implementation(Dependency.Network.RETROFIT_RXJAVA)
    implementation(Dependency.Network.RETROFIT_GSON)

    // rxjava
    implementation(Dependency.Asynchronous.RX_JAVA)
    implementation(Dependency.Asynchronous.RX_ANDROID)

    // coroutines
    implementation(Dependency.Asynchronous.COROUTINES)

    // hilt
    kapt(Dependency.DI.HILT_ANDROID_COMPILER)
    implementation(Dependency.DI.HILT)

    // test
    testImplementation(Dependency.Mock.MOCKITO)
    androidTestImplementation(Dependency.Mock.MOCKITO_ANDROID)
    testImplementation(Dependency.Mock.MOCKK)
    testImplementation(Dependency.Test.J_UNIT)
    testImplementation(Dependency.Test.ANDROIDX_TEST)
    testImplementation(Dependency.Test.ARCH_CORE_TEST)
    androidTestImplementation(Dependency.Test.ANDROIDX_RULES_TEST)
    androidTestImplementation(Dependency.Test.ANDROIDX_RUNNER_TEST)
    androidTestImplementation(Dependency.Test.ESPRESSO_CORE)

}
