plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.redstevo.ecomerce_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.redstevo.ecomerce_app"
        minSdk = 24
        targetSdk = 35
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

    packaging {
        resources {
            excludes += "META-INF/DEPENDENCIES"
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.flexbox)
    implementation(libs.picasso)
    implementation(libs.firebase.database)
    implementation(platform(libs.firebase.bom))
    implementation(libs.google.firebase.auth)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.cloudinary.android)
    implementation(libs.algoliasearch.core)
    implementation(libs.algoliasearch.apache)
    implementation(libs.meilisearch.java)

}
