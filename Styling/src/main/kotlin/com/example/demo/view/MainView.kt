package com.example.demo.view

import com.example.demo.app.Styles
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = hbox {
        paddingAll = 30.0

        label(title) {
            addClass(Styles.heading)
        }
        button {
            text = "T"
            prefWidth = 150.0
            prefHeight = 80.0
            addClass(Styles.myStyle)
        }
        button {
            text = "A"
            prefWidth = 150.0
            prefHeight = 80.0
            addClass(Styles.myStyle)
        }
    }
}
