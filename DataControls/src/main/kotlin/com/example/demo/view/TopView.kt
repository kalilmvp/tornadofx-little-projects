package com.example.demo.view

import com.example.demo.controllers.MainController
import tornadofx.*

class TopView : View("My View") {
    val mainController: MainController by inject()
    override val root = listview(mainController.students) {
        prefHeight = 245.0
        cellFormat {
            text = it
            if (text.contains("Gina")) {
                textFill = c("red", 0.4)
                style {
                    fontSize = 20.px
                }
            }
        }
    }
}
