// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Dependency.BuildPlugin.ANDROID_GRADLE)
        classpath(Dependency.BuildPlugin.KOTLIN_GRADLE)
        classpath(Dependency.BuildPlugin.KOTLIN_EXT)
        classpath(Dependency.BuildPlugin.NAVIGATION_ARGS)
        classpath(Dependency.BuildPlugin.HILT)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
