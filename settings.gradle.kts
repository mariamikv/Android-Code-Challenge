pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Android Code Challenge"
include(":app")
include(":core")
project(":core").projectDir = File("core")
include(":core:data")
project(":core:data").projectDir = File("core/data")
include(":core:ui")
project(":core:ui").projectDir = File("core/ui")
include(":core:domain")
project(":core:domain").projectDir = File("core/domain")
include(":core:navigation")
project(":core:navigation").projectDir = File("core/navigation")
include(":feature")
project(":feature").projectDir = File("feature")
include(":feature:home")
project(":feature:home").projectDir = File("feature/home")
include(":feature:details")
project(":feature:details").projectDir = File("feature/details")
