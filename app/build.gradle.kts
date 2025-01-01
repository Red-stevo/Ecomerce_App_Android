plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.redstevo.ecomerce_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.redstevo.ecomerce_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
}

dependencies {
    // Core AndroidX Dependencies
    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.material)

    // Networking Libraries
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)

    // Flexbox Layout Dependency
    implementation(libs.flexbox)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    // Glide
//    implementation(libs.glide)
//    kapt(libs.glide.compiler) // Use kapt for annotation processing
}
