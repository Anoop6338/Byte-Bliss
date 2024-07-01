plugins {
    alias(libs.plugins.android.application)
}



android {
    namespace = "com.example.bytebliss"
    compileSdk = 34

    buildFeatures{
        viewBinding= true;
    }

    defaultConfig {
        applicationId = "com.example.bytebliss"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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

    // gif display using koral
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28")

    //room implementation
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    // glide implementation
    implementation ("com.github.bumptech.glide:glide:4.16.0")



}