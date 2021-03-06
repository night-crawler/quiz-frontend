import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.jetbrains.kotlin.js") version "1.3.70"
    id("com.palantir.docker") version "0.25.0"
    kotlin("plugin.serialization") version "1.3.70"
    id("org.jlleitschuh.gradle.ktlint") version "9.1.1"
    id("com.dorongold.task-tree") version "1.5"
}

group = "fm.force"
version = "1.0.1-SNAPSHOT"

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
    jcenter()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers/") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx.html/") }
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")

                implementation("io.github.microutils:kotlin-logging-js:1.7.9")
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

                implementation(npm("@material-ui/core", "4.9.8"))
                implementation(npm("@material-ui/icons", "4.9.1"))
                implementation(npm("styled-components", "5.0.1"))
                implementation(npm("inline-style-prefixer", "5.1.2"))
                implementation(npm("history", "4.10.1"))
                implementation(npm("connected-react-router", "5.0.1"))

                // react-router-dom 4.3.1-pre.91 is broken: it does not update the view
                implementation(npm("react-router-dom", "5.1.2"))
                implementation(npm("react-helmet", "5.2.1"))
                implementation(npm("redux-form", "8.3.5"))

                // ktor needs it
                implementation(npm("abort-controller"))
                implementation(npm("text-encoding"))
                implementation(npm("utf-8-validate"))
                implementation(npm("bufferutil"))

                implementation(npm("@material-ui/lab", "4.0.0-alpha.47"))

                implementation(npm("react-window", "1.8.5"))
                implementation(npm("react-window-infinite-loader", "1.0.5"))
                implementation(npm("react-virtualized-auto-sizer", "1.0.2"))

                implementation(npm("date-fns", "2.12.0"))
                implementation(npm("codemirror", "5.53.2"))
                implementation(npm("react-codemirror2", "7.1.0"))
                implementation(npm("style-loader"))
                implementation(npm("css-loader"))
                implementation(npm("react-use", "14.2.0"))

                implementation(npm("react-syntax-highlighter", "12.2.1"))
                implementation(npm("markdown-it", "10.0.0"))
                implementation(npm("prismjs", "1.20.0"))
                implementation(npm("prism-themes", "1.4.0"))
                implementation(npm("sanitize-html", "1.23.0"))
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
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
}

docker {
    name = "ncrawler/${project.name}:$version"
    files("$buildDir/distributions")
    pull(false)
    noCache(false)
    dependsOn(tasks["browserProductionWebpack"])
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
