plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin ("kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "xyz.thisisjames.boulevard.android.podium"
    compileSdk = 33

    defaultConfig {
        applicationId = "xyz.thisisjames.boulevard.android.podium"
        minSdk = 26
        targetSdk = 33
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }


}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.github.ibrahimsn98:CirclesLoadingView:1.0")



    //splashscreen api
    implementation("androidx.core:core-splashscreen:1.0.1")


    // Room components
    implementation ("androidx.room:room-ktx:2.3.0")
    kapt ("androidx.room:room-compiler:2.3.0")
    androidTestImplementation( "androidx.room:room-testing:2.3.0")



    // Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.13.0")
    // Retrofit with Moshi Converter
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")


    //Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")



    //dot indicator
    implementation("com.tbuonomo:dotsindicator:5.0")

    // cicular indicator for scores
    implementation("com.github.antonKozyriatskyi:CircularProgressIndicator:1.3.0")


    // markdown preview
    implementation("com.github.mukeshsolanki:MarkdownView-Android:1.1.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}