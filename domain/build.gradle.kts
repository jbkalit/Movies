plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = Version.compile_sdk
    buildToolsVersion = Version.build_tools

    defaultConfig {
        minSdk = Version.min_sdk
        targetSdk = Version.target_sdk
        versionCode = Version.version_code
        versionName = Version.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    // hilt
    kapt(Dependency.DI.HILT_ANDROID_COMPILER)
    implementation(Dependency.DI.HILT)

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

    // gson
    implementation(Dependency.Gson.GSON)

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
