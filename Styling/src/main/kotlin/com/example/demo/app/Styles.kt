package com.example.demo.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val myStyle by cssclass()

        private val topColor = c("#cedede")
        private val rightColor = c("green")
        private val leftColor = Color.RED
        private val bottomColor = Color.BLUE
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        myStyle {
            fontWeight = FontWeight.EXTRA_BOLD
            fontSize = 10.px
            backgroundColor += c("pink")
            borderRadius += box(4.px)
            borderColor = multi(box(
                    top = topColor,
                    right = rightColor,
                    left = leftColor,
                    bottom = bottomColor
            ))
        }
    }
}
