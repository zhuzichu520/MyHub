plugins {
    id("com.android.application")
    id("com.github.ben-manes.versions")
    kotlin("android")
    kotlin("kapt")
}

Config.initJenkinsProperties(project)

android {

    compileSdkVersion(Config.compileSdkVersion())

    signingConfigs {
        create("appSign") {
            keyAlias = Config.keyAlias()
            keyPassword = Config.keyPassword()
            storeFile = file(Config.storeFile())
            storePassword = Config.storePassword()
        }
    }

    defaultConfig {
        applicationId = Config.applicationId()
        minSdkVersion(Config.minSdkVersion())
        targetSdkVersion(Config.targetSdkVersion())
        versionCode = Config.versionCode()
        versionName = Config.versionName()
        signingConfig = signingConfigs.getByName("appSign")
        multiDexEnabled = true
//        renderscriptTargetApi = 18
//        renderscriptSupportModeEnabled = true
        resValue("string", "app_name_new", Config.appName())
        manifestPlaceholders.apply {
            put("ic_launcher_new", "@mipmap/ic_launcher")
            put("ic_launcher_round_new", "@mipmap/ic_launcher_round")
        }

        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        sourceSets["main"].apply {
            java.srcDir("src/main/kotlin")
        }
    }

    buildFeatures.dataBinding = true

}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))
    Config.denpendModules(project)
    api(project(path = ":library-shared"))
    kapt(Kapts.AROUTER_COMPILER)
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.5")
    androidTestImplementation(AndroidTestingLibs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLibs.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLibs.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(AndroidTestingLibs.ESPRESSO_CORE)
    testImplementation(TestingLibs.JUNIT)
}
