package com.example.demo.view

import javafx.geometry.Pos
import tornadofx.*

class LeftView : View("Left View") {
    override val root = hbox {
        alignment = Pos.CENTER
        label("Left View")
    }
}
