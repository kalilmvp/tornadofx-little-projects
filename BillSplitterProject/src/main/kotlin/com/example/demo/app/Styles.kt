package com.example.demo.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val vbox by cssclass()
        val field_label by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            textFill = Color.WHITE
        }

        vbox {
            padding = box(10.px)
            backgroundColor += c(red = 0.0, green = 25.0/255, blue = 51.0/255)
        }

        field_label {
            fontSize = 12.px
            textFill = Color.WHITE
        }

        form {
            fieldset {
                label {
                    fontSize = 12.px
                    textFill = Color.WHITE
                }
            }
        }
    }
}
