import org.beryx.jlink.data.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.2.71"
    application
    id("com.zyxist.chainsaw") version "0.3.1"
    id("org.beryx.jlink") version "2.0.0"
}

val currentOS = org.gradle.internal.os.OperatingSystem.current()
val platform = when {
        currentOS.isWindows() -> "win"
        currentOS.isLinux() -> "linux"
        currentOS.isMacOsX() -> "mac"
        else -> throw GradleException("Unsupported operating system: $currentOS")
}
val javaFxVersion = 11

//Fixes module compilation for Kotlin in "com.zyxist.chainsaw"
val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir

application {
    mainClassName = "org.beryx.jlink.test.kotlin.HelloWorldApp"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.2.70")
    implementation("org.openjfx:javafx-base:${javaFxVersion}:${platform}")
    implementation("org.openjfx:javafx-controls:${javaFxVersion}:${platform}") {
        exclude(module = "javafx-graphics")
    }
    implementation("org.openjfx:javafx-fxml:${javaFxVersion}:${platform}") {
        exclude(module = "javafx-controls")
    }
    implementation("org.openjfx:javafx-graphics:${javaFxVersion}:${platform}") {
        exclude(module = "javafx-base")
    }
    implementation("no.tornado:tornadofx:1.7.17")
}

jlink{
    launcher (delegateClosureOf<LauncherData> {
        name = "hello"
    })    
    addExtraDependencies("javafx")
    imageZip.set(project.file("${project.buildDir}/image-zip/hello-image.zip"))
}
