plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.dokka)
    alias(libs.plugins.mavenPublish)
}

android {
    namespace = "com.king.mlkit.vision.object"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.espressoCore)

    compileOnly(libs.androidxAppcompat)
    api(libs.googleMlkitObjectDetection)

    compileOnly(project(":mlkit-common"))
}
