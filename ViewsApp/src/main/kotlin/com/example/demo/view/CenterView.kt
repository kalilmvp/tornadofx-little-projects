package com.example.demo.view

import javafx.geometry.Pos
import tornadofx.*

class CenterView : View("Center View") {
    override val root = hbox {
        alignment = Pos.CENTER
        label("Center View")
    }
}
