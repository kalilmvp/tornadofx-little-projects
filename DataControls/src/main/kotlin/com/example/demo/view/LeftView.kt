package com.example.demo.view

import com.example.demo.controllers.MainController
import com.example.demo.model.Student
import com.example.demo.model.StudentModel
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.LocalDate

class LeftView : View("Left View") {
    val mainController: MainController by inject()
    val model: StudentModel by inject()

    /*val firstName = SimpleStringProperty()
    val lastName = SimpleStringProperty()
    val dateOfBirth = SimpleObjectProperty<LocalDate>()*/

    override val root = form {
        fieldset {
            field(text = "First Name") {
                textfield(model.firstName) {
                    required()
                    /*validator {
                        if (it.isNullOrBlank()) error("This is required") else null
                    }*/
                }
            }
            field(text = "Last Name") {
                textfield(model.lastName).required()
            }
            field(text = "Date of birth") {
                datepicker(model.birthDay).required()
            }
            hbox(5){
                button {
                    text = "Save"
                    enableWhen(model.dirty)
                    action {
                        model.commit {
                            // save to our datasource
                            val student = Student(1,
                                    model.firstName.value,
                                    model.lastName.value,
                                    model.birthDay.value)

                            mainController.addStudentData(student)
                        }
                    }
                }
                button {
                    text = "Rest"
                    enableWhen(model.dirty)
                    action {
                        model.rollback()
                    }
                }
            }
        }
    }
}
