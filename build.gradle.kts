import org.beryx.jlink.data.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.3.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.5"
    id("org.beryx.jlink") version "2.4.0"
}

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir

application {
    mainClassName = "test.kotlin/org.beryx.jlink.test.kotlin.HelloWorldApp"
}

repositories {
    mavenCentral()
}

javafx {
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.10")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.10")
    implementation("no.tornado:tornadofx:1.7.17") {
        exclude("org.jetbrains.kotlin")
    }
}

jlink{
    launcher {
        name = "hello"
    }
    addExtraDependencies("javafx")
    imageZip.set(project.file("${project.buildDir}/image-zip/hello-image.zip"))
}
