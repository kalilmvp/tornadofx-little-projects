package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controllers.MainController
import javafx.geometry.Pos
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    private val mainController: MainController by inject()

    override val root = vbox {
        alignment = Pos.CENTER
        spacing = 30.0
        label(mainController.labelText) {
            addClass(Styles.heading)
        }
        button {
            this.text = "Click me"
            action {
                mainController.getRandomName()
            }
        }
    }
}
