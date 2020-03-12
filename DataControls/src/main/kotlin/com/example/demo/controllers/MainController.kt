package com.example.demo.controllers

import com.example.demo.model.Student
import javafx.collections.FXCollections
import tornadofx.*
import java.time.LocalDate

class MainController: Controller() {

    val students = FXCollections.observableArrayList<String>(
            "Gina",
            "James Bond",
            "Helena Mt"
    )

    val studentsData = FXCollections.observableArrayList<Student>(
            Student(1, "Kalil", "Peixoto",
                    LocalDate.of(1986, 10, 25)),
            Student (2, "Ailsa", "Coto",
                LocalDate.of(1990, 7, 29))

    )

    fun addStudent(fullName:String) {
        students.add(fullName)
    }

    fun addStudentData(student: Student) {
        studentsData.add(student)

    }
}
