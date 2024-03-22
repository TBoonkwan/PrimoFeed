pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PrimoFeed"
include(":app")
include(":data")
include(":domain")
include(":model")
include(":feature")
include(":core")
include(":common-ui")
include(":database")
