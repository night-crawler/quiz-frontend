plugins {
    kotlin("js") version "1.3.61"
}

apply {
    plugin("kotlin-dce-js")
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

repositories {
    mavenCentral()
    maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers/") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx.html/") }
    maven { url = uri("https://dl.bintray.com/cfraser/muirwik") }
}
dependencies {

    implementation(kotlin("stdlib-js"))

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
    target {
        browser {
            compilations.all {
                kotlinOptions {
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

        implementation(npm("react-router-dom", reactRouterDomVersion.split("-").first()))

        implementation(npm("core-js", "3.6.4"))

        implementation(npm("@material-ui/core"))
        implementation(npm("@material-ui/icons"))
        implementation(npm("styled-components"))
        implementation(npm("inline-style-prefixer"))
    }
}
