plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("org.jetbrains.dokka") version "1.5.30" apply false
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}