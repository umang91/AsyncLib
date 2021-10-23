buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val libs = project.extensions.getByType<VersionCatalogsExtension>()
            .named("libs") as org.gradle.accessors.dm.LibrariesForLibs
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath(libs.gradlePluginKotlin)
        // publishing plugin
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
        // documentation plugin
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.5.30")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}