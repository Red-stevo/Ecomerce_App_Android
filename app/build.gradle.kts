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
    // implementation(libs.recyclerview.v7)
    // implementation(libs.cardview.v7)

    // Unit Testing Dependencies
    //testImplementation(libs.junit)

    // Instrumented Testing Dependencies
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)

    // Networking Libraries
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)

    // Flexbox Layout Dependency
    implementation(libs.flexbox)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
