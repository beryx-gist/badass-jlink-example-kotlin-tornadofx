package org.beryx.jlink.test.kotlin

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class HelloWorldApp : App() {
    override val primaryView = HelloWorld::class
    init {
        importStylesheet(HelloStyles::class)
    }
}

class HelloWorld : View() {
    override val root = hbox {
        label("Hello, world!")
    }
}

class HelloStyles : Stylesheet() {
    companion object {
        val label by cssclass()
    }
    init {
        label {
            fontSize = 64.px
            fontWeight = FontWeight.BOLD
            textFill = Color.BLUE
        }
    }
}
