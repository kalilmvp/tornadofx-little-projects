package com.example.demo.view

import com.example.demo.controllers.MainController
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class BottomView : View("My View") {
    val mainController: MainController by inject()
    val firstName = SimpleStringProperty()
    val lastName = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Add first name") {
                textfield(firstName)
            }
            field("Add Last name") {
                textfield(lastName)
            }
        }

        hbox {
            button {
                text = "Save student"
                action {
                    if (firstName.get().isNullOrEmpty() || lastName.get().isNullOrEmpty()) {
                        // for now we do nothing
                    } else {
                        mainController.addStudent(firstName.concat(" ").concat(lastName).get())
                        firstName.set("")
                        lastName.set("")
                    }
                }
            }
        }

    }
}
