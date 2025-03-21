plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.jetbrains.kotlin.android)
   alias(libs.plugins.jetbrains.kotlin.serialization)
   alias(libs.plugins.compose.compiler)
   id("org.jetbrains.kotlin.kapt")
}

configurations.all {
   resolutionStrategy.eachDependency {
      if (requested.group == "org.jetbrains.kotlinx" && requested.name == "kotlinx-metadata-jvm") {
         useVersion("0.6.0")
      }
   }
}


android {
   namespace = "com.zybooks.petadoption"
   compileSdk = 34

   defaultConfig {
      applicationId = "com.zybooks.petadoption"
      minSdk = 24
      targetSdk = 34
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      vectorDrawables {
         useSupportLibrary = true
      }
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
   kotlinOptions {
      jvmTarget = "1.8"
   }
   buildFeatures {
      compose = true
   }
   composeOptions {
      kotlinCompilerExtensionVersion = "1.5.1"
   }
   packaging {
      resources {
         excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
   }
}

dependencies {
   implementation(libs.androidx.navigation.compose)
   implementation(libs.kotlinx.serialization.json)
   implementation(libs.androidx.lifecycle.viewmodel.compose)
   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.lifecycle.runtime.ktx)
   implementation(libs.androidx.activity.compose)
   implementation(platform(libs.androidx.compose.bom))
   implementation(libs.androidx.ui)
   implementation(libs.androidx.ui.graphics)
   implementation(libs.androidx.ui.tooling.preview)
   implementation(libs.androidx.material3)
   implementation(libs.androidx.room.common)
   implementation(libs.androidx.foundation.layout.android)
   implementation(libs.androidx.room.ktx)
   implementation(libs.androidx.lifecycle.livedata.ktx)
   implementation(libs.androidx.runtime.livedata)
   implementation(libs.kotlinx.metadata.jvm)
   kapt("androidx.room:room-compiler:2.6.1")

   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
   androidTestImplementation(platform(libs.androidx.compose.bom))
   androidTestImplementation(libs.androidx.ui.test.junit4)
   debugImplementation(libs.androidx.ui.tooling)
   debugImplementation(libs.androidx.ui.test.manifest)
}


