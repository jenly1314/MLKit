plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.king.mlkit.vision.app"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.king.mlkit.vision.app"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = properties["VERSION_CODE"].toString().toInt()
        versionName = properties["VERSION_NAME"].toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters += "armeabi-v7a"
        }
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

    implementation(libs.androidxMaterial)
    implementation(libs.androidxAppcompat)
    implementation(libs.androidxConstraintlayout)
    implementation(libs.androidxCoreKtx)
    implementation(libs.appDialog)

    implementation(project(":mlkit-common"))
    implementation(project(":mlkit-barcode-scanning"))
    implementation(project(":mlkit-face-detection"))
    implementation(project(":mlkit-face-mesh-detection"))
    implementation(project(":mlkit-image-labeling"))
    implementation(project(":mlkit-object-detection"))
    implementation(project(":mlkit-pose-detection"))
    implementation(project(":mlkit-pose-detection-accurate"))
    implementation(project(":mlkit-segmentation-selfie"))
    implementation(project(":mlkit-text-recognition"))
}
