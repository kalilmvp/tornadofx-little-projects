package com.example.demo.controllers

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainController: Controller() {

    //MUTABLE
    var labelText = SimpleStringProperty("Initial text")
    //CONSTANT - NOT MUTABLE
    private val namesList = listOf(
            "Name 01",
            "Name 02",
            "Name 03",
            "Name 04",
            "Name 05",
            "Name 06"
    )

    fun getRandomName() {
        val randomInteger: Int = namesList.indices.shuffled().first()
        println("Random integer: $randomInteger")
        labelText.set(namesList[randomInteger])
    }
}
