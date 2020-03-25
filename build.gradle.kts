import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.jetbrains.kotlin.js") version "1.3.70"
//    id("org.jetbrains.kotlin.multiplatform") version "1.3.70"
    kotlin("plugin.serialization") version "1.3.70"
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

kotlin {
    target {
        browser {
            @UseExperimental(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalDceDsl::class)
            dceTask {
                // dceOptions
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
            }
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
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }

    sourceSets {
        named("main") {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.serialization.ImplicitReflectionSerializer")
                useExperimentalAnnotation("kotlinx.serialization.UnstableDefault")
            }

            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:0.20.0")

                //    implementation("io.ktor:ktor-client-js:$ktorClientVersion")
                //    implementation("io.ktor:ktor-client-json-js:$ktorClientVersion")
                //    implementation("io.ktor:ktor-client-serialization-js:$ktorClientVersion")

                implementation("org.jetbrains:kotlin-extensions:$extensionsVersion-kotlin-$kotlinVersion")

                implementation("org.jetbrains:kotlin-react:$reactVersion-kotlin-$kotlinVersion")
                implementation("org.jetbrains:kotlin-react-dom:$reactVersion-kotlin-$kotlinVersion")

                implementation("org.jetbrains:kotlin-react-router-dom:$reactRouterDomVersion-kotlin-$kotlinVersion")

                implementation("org.jetbrains:kotlin-redux:$reduxVersion-kotlin-$kotlinVersion")
                implementation("org.jetbrains:kotlin-react-redux:$reactReduxVersion-kotlin-$kotlinVersion")

                implementation("org.jetbrains:kotlin-styled:$styledVersion-kotlin-$kotlinVersion")

                implementation("com.ccfraser.muirwik:muirwik-components:0.4.1")

                //        testImplementation(kotlin("test-js"))
                implementation("com.benasher44:uuid:0.1.0")

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
                implementation(npm("utf-8-validate"))
                implementation(npm("bufferutil"))
            }
        }

        named("test") {
            dependencies {
                implementation(npm("core-js", "3.6.4"))
                implementation(kotlin("stdlib-js"))
                implementation(kotlin("test-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")
                implementation("io.kotest:kotest-core:4.0.0-BETA1")
                implementation("io.kotest:kotest-core:4.0.0-BETA1")
                implementation("io.kotest:kotest-assertions:4.0.0-BETA1")
            }
        }
    }
}

tasks.withType<Kotlin2JsCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.ExperimentalStdlibApi"
    kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
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
