pluginManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "Movies"
include(
    ":app",
    ":data",
    ":domain"
)
