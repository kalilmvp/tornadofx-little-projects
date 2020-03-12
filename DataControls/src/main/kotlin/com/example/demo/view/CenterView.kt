package com.example.demo.view

import com.example.demo.controllers.MainController
import com.example.demo.model.Student
import com.example.demo.model.StudentModel
import tornadofx.*

class CenterView : View("My View") {
    val mainController: MainController by inject()
    val model: StudentModel by inject()

    override val root = tableview<Student> {
        items = mainController.studentsData

        isEditable = true

        column("ID", Student::idProperty)
        column("First Name", Student::firstNameProperty)
        column("Last Name", Student::lastNameProperty)
        readonlyColumn("Age", Student::age)

        bindSelected(model)
    }
}
