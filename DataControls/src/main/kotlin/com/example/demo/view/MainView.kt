package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controllers.MainController
import javafx.collections.FXCollections
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    /*val studentNames = listOf(
            "Gina",
            "James Bond",
            "Helena Mt"
    ).observable()*/

    //val topView: TopView by inject()
    val leftView: LeftView by inject()
    val centerView: CenterView by inject()
    //val bottomView: BottomView by inject()


    override val root = borderpane {
        //top = topView.root
        left = leftView.root
        center = centerView.root
        //bottom = bottomView.root

        /*listview(mainController.students) {
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
        *//*listview<String> {
            items.add("Test 01")
            items.add("Test 02")
            items.add("Test 03")
            items.add("Test 04")
        }*//*

        button {
            text = "Add Student"
            action {
                mainController.addStudent("New student")
            }
        }*/
    }
}
