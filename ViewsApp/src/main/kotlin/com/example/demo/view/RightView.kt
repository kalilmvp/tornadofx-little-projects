package com.example.demo.view

import javafx.geometry.Pos
import tornadofx.*

class RightView : View("Right View") {
    override val root = hbox {
        alignment = Pos.CENTER
        label("Right View")
    }
}
