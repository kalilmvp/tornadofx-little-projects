package com.example.demo.view

import javafx.geometry.Pos
import tornadofx.*

class BottomView : View("Bottom View") {
    override val root = hbox {
        alignment = Pos.CENTER
        label("Bottom View")
    }
}
