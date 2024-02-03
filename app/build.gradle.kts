plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.gemini"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gemini"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.google.ai.client.generativeai:generativeai:0.1.2")

    // Required for one-shot operations (to use `ListenableFuture` from Reactive Streams)
    implementation("com.google.guava:guava:31.0.1-android")

    // Required for streaming operations (to use `Publisher` from Guava Android)
    implementation("org.reactivestreams:reactive-streams:1.0.4")

    implementation ("com.airbnb.android:lottie:6.3.0")

}