package com.example.demo.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import java.time.LocalDate
import java.time.Period
import tornadofx.*

/*class Student(val id: Int, val firstName: String, val lastName: String,
              val birthDay: LocalDate) {

    val age: Int get() = Period.between(birthDay, LocalDate.now()).years
}*/

class Student(id: Int, firstName: String, lastName: String,
              birthDay: LocalDate) {

    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val firstNameProperty = SimpleStringProperty(firstName)
    var firstName by firstNameProperty

    val lastNameProperty = SimpleStringProperty(lastName)
    var lastName by lastNameProperty

    val birthDayProperty = SimpleObjectProperty<LocalDate>(birthDay)
    var birthDay by birthDayProperty

    val age: Int get() = Period.between(birthDay, LocalDate.now()).years
}

class StudentModel: ItemViewModel<Student>() {
    val id = bind { item?.idProperty  }
    val firstName = bind { item?.firstNameProperty }
    val lastName = bind { item?.lastNameProperty }
    val birthDay = bind { item?.birthDayProperty }
}
