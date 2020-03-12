package com.example.demo.view

import javafx.geometry.Pos
import tornadofx.*

class TopView : View("Top View") {
    override val root = hbox {
        alignment = Pos.CENTER
        spacing = 16.0
        label("My View")
        button("Menu")
        button("File")
    }
}
