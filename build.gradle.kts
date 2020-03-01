import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.jetbrains.kotlin.js") version "1.3.70-eap-274"
    id("org.jlleitschuh.gradle.ktlint") version "9.1.1"
    id("com.dorongold.task-tree") version "1.5"
}

group = "fm.force"
version = "1.0-SNAPSHOT"

val kotlinVersion: String by extra
val reactVersion: String by extra
val extensionsVersion: String by extra
val reactRouterDomVersion: String by extra
val reactReduxVersion: String by extra
val reduxVersion: String by extra
val styledVersion: String by extra
val ktorClientVersion: String by extra

repositories {
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers/") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx.html/") }
    maven { url = uri("https://dl.bintray.com/cfraser/muirwik") }
}
dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("io.ktor:ktor-client-js:$ktorClientVersion")
    implementation("io.ktor:ktor-client-core:$ktorClientVersion")
    implementation("io.ktor:ktor-client-json:$ktorClientVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")

    implementation("org.jetbrains:kotlin-extensions:$extensionsVersion-kotlin-$kotlinVersion")

    implementation("org.jetbrains:kotlin-react:$reactVersion-kotlin-$kotlinVersion")
    implementation("org.jetbrains:kotlin-react-dom:$reactVersion-kotlin-$kotlinVersion")

    implementation("org.jetbrains:kotlin-react-router-dom:$reactRouterDomVersion-kotlin-$kotlinVersion")

    implementation("org.jetbrains:kotlin-redux:$reduxVersion-kotlin-$kotlinVersion")
    implementation("org.jetbrains:kotlin-react-redux:$reactReduxVersion-kotlin-$kotlinVersion")

    implementation("org.jetbrains:kotlin-styled:$styledVersion-kotlin-$kotlinVersion")

    implementation("com.ccfraser.muirwik:muirwik-components:0.4.1")

    testImplementation(kotlin("test-js"))
}

kotlin {
//        sourceSets {
//        val main by getting {
//            languageSettings.apply {
//                useExperimentalAnnotation("kotlin.collections.ArrayDeque.ArrayDeque")
//                useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
//            }
//        }
//    }

    target {
        browser {
            compilations.all {
                kotlinOptions {
                    friendModulesDisabled = false
                    metaInfo = true
                    sourceMap = true
                    sourceMapEmbedSources = "always"
                    moduleKind = "commonjs"
                    main = "call"
                }
            }
        }
    }

    sourceSets["main"].dependencies {
        implementation(npm("react", reactVersion.split("-").first()))
        implementation(npm("react-dom", reactVersion.split("-").first()))

        implementation(npm("redux", reduxVersion.split("-").first()))
        implementation(npm("react-redux", reactReduxVersion.split("-").first()))

        implementation(npm("core-js", "3.6.4"))

        implementation(npm("@material-ui/core", "4.9.3"))
        implementation(npm("@material-ui/icons", "4.9.1"))
        implementation(npm("styled-components", "5.0.1"))
        implementation(npm("inline-style-prefixer", "5.1.2"))
        implementation(npm("history", "4.10.1"))
        implementation(npm("connected-react-router", "5.0.1"))

        // react-router-dom 4.3.1-pre.91 is broken: it does not update the view
        implementation(npm("react-router-dom", "5.1.2"))
        implementation(npm("react-helmet", "5.2.1"))
        implementation(npm("redux-form", "8.2.6"))

        // ktor needs it
        implementation(npm("abort-controller"))
        implementation(npm("text-encoding"))
    }
}

tasks.withType<Kotlin2JsCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.ExperimentalStdlibApi"
}

ktlint {
    verbose.set(true)
    android.set(false)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(true)
    enableExperimentalRules.set(true)
    additionalEditorconfigFile.set(file("./.editorconfig"))

    disabledRules.set(setOf("experimental:multiline-if-else"))

    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}
