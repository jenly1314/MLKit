pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application",
                "com.android.library" -> useModule("com.android.tools.build:gradle:${requested.version}")
                "org.jetbrains.kotlin.android" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MLKit"
include(
    ":app",
    ":mlkit-barcode-scanning",
    ":mlkit-common",
    ":mlkit-face-detection",
    ":mlkit-face-mesh-detection",
    ":mlkit-image-labeling",
    ":mlkit-object-detection",
    ":mlkit-pose-detection",
    ":mlkit-pose-detection-accurate",
    ":mlkit-segmentation-selfie",
    ":mlkit-text-recognition",
)
