import com.android.build.gradle.internal.dsl.TestOptions
import ext.androidCoreModule
import ext.coreModule
import ext.dbModule
import ext.localRepoModule
import ext.remoteRepoModule
import ext.uiModule
import ext.useCaseModule

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        applicationId = "com.github.pavlospt.refactoredumbrella"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.incremental", "true")
            }
        }
    }

    viewBinding.isEnabled = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    testOptions { testOptions() }
}

dependencies {
    kapt(Deps.AndroidX.Room.COMPILER)

    implementation(kotlin("stdlib-jdk8"))

    implementation(androidCoreModule("adapter"))
    implementation(androidCoreModule("viewbinding"))

    implementation(coreModule("dispatchers"))
    implementation(coreModule("domain"))
    implementation(coreModule("result"))
    implementation(coreModule("usecase"))

    // Github core stack
    implementation(dbModule("github"))
    implementation(localRepoModule("github"))
    implementation(remoteRepoModule("github"))
    implementation(useCaseModule("github"))

    // Dashboard feature
    implementation(uiModule("dashboard"))

    implementation(Deps.Kotlinx.Coroutines.CORE)
    implementation(Deps.Kotlinx.Coroutines.ANDROID)

    implementation(Deps.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)
    implementation(Deps.AndroidX.Core.KTX)
    implementation(Deps.AndroidX.Lifecycle.RUNTIME)
    implementation(Deps.AndroidX.Lifecycle.RUNTIME_KTX)
    implementation(Deps.AndroidX.Lifecycle.COMMON_JAVA8)
    implementation(Deps.AndroidX.Lifecycle.VIEWMODEL_KTX)
    implementation(Deps.AndroidX.Lifecycle.LIVEDATA_KTX)
    implementation(Deps.AndroidX.Lifecycle.EXTENSIONS)
    implementation(Deps.AndroidX.Navigation.FRAGMENT)
    implementation(Deps.AndroidX.Navigation.FRAGMENT_KTX)
    implementation(Deps.AndroidX.Navigation.UI)
    implementation(Deps.AndroidX.Navigation.UI_KTX)
    implementation(Deps.AndroidX.Room.RUNTIME)
    implementation(Deps.AndroidX.Room.KTX)

    implementation(Deps.Google.Material.MATERIAL)

    implementation(Deps.Square.OkHttp.OKHTTP)
    implementation(Deps.Square.OkHttp.LOGGING_INTERCEPTOR)
    implementation(Deps.Square.Retrofit.RETROFIT)
    implementation(Deps.Square.Retrofit.Converters.MOSHI)
    implementation(Deps.Square.Moshi.KOTLIN)
    implementation(Deps.Square.Moshi.ADAPTERS)

    implementation(Deps.Koin.CORE)
    implementation(Deps.Koin.CORE_EXT)
    implementation(Deps.Koin.ANDROID)
    implementation(Deps.Koin.ANDROID_EXT)
    implementation(Deps.Koin.ANDROIDX_SCOPE)
    implementation(Deps.Koin.ANDROIDX_VIEWMODEL)
    implementation(Deps.Koin.ANDROIDX_FRAGMENT)
    implementation(Deps.Koin.ANDROIDX_EXT)

    implementation(Deps.Coil.COIL)

    implementation(Deps.FlowBinding.ANDROID)
    implementation(Deps.FlowBinding.SWIPE_REFRESH_LAYOUT)

    // Test Deps
    testImplementation(TestDeps.JUnit.JUNIT)
    testImplementation(TestDeps.Kotlinx.COROUTINES_TEST)
    testImplementation(TestDeps.AndroidX.Arch.CORE_TESTING)
}

fun Test.testLogging() {
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
        outputs.upToDateWhen { false }
        showStandardStreams = true
    }
}

fun TestOptions.testOptions() {
    unitTests.delegateClosureOf<Test> { testLogging() }
}
