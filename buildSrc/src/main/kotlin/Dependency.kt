object Dependency {

    object Module {
        const val app = ":app"
        const val data = ":data"
        const val domain = ":domain"
        const val presentation = ":presentation"
    }

    object BuildPlugin {
        const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Version.gradle}"
        const val KOTLIN_GRADLE  = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.coreKtx}"
        const val KOTLIN_EXT = "org.jetbrains.kotlin:kotlin-android-extensions:${Version.coreKtx}"
        const val ANDROID_JUNIT = "de.mannodermaus.gradle:plugins.android-junit5:1.6.2.0"
        const val NAVIGATION_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
        const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt_android}"
    }

    const val CORE_KTX = "androidx.core:core-ktx:${Version.coreKtx}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val MATERIAL = "com.google.android.material:material:${Version.material}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Version.constraint}"
    const val LIFECYCLE_EXTENSION = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.fragment}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    const val KOTLIN_STANDARD_LIBRARY = "org.jetbrains.kotlin:kotlin-stdlib:${Version.gradlePlugin}"
    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Version.legacy}"


    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val RETROFIT_RXJAVA = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofit}"
        const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val OK_HTTP = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
        const val OK_HTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
    }

    object Gson {
        const val GSON = "com.google.code.gson:gson:${Version.gson}"
    }

    object DI {
        const val HILT = "com.google.dagger:hilt-android:${Version.hilt_android}"
        const val HILT_TESTING = "com.google.dagger:hilt-android-testing:${Version.hilt_android}"
        const val HILT_COMMON = "com.google.dagger:hilt-common:${Version.hilt}"
        const val HILT_LIFECYCLE = "com.google.dagger:hilt-lifecycle-viewmodel:${Version.hilt}"
        const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Version.hilt}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.hilt_android}"
    }

    object Asynchronous {
        const val RX_JAVA = "io.reactivex.rxjava2:rxjava:${Version.rx_java}"
        const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${Version.rx_android}"

        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
    }

    object Glide {
        const val GLIDE = "com.github.bumptech.glide:glide:${Version.glide}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Version.glide}"
    }

    object Mock {
        const val MOCKITO = "org.mockito:mockito-core:${Version.mockito}"
        const val MOCKITO_ANDROID = "org.mockito:mockito-android:${Version.mockito}"

        const val MOCKK = "io.mockk:mockk:${Version.mockk}"
    }

    object Pagination {
        const val PAGING = "androidx.paging:paging-runtime:${Version.paging}"
    }

    object Test {
        const val ANDROIDX_TEST = "androidx.test:core-ktx:${Version.coreKtx}"
        const val ARCH_CORE_TEST = "android.arch.core:core-testing:${Version.arch_core_test}"
        const val ANDROIDX_RULES_TEST = "androidx.test:rules:${Version.rules}"
        const val ANDROIDX_RUNNER_TEST = "androidx.test:runner:${Version.runner}"
        const val J_UNIT = "junit:junit:${Version.jUnit}"
        const val J_UNIT_EXT = "androidx.test.ext:junit:${Version.junitExt}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val TEST_ANNOTATION = "androidx.annotation:annotation:${Version.testAnnotation}"
    }

}