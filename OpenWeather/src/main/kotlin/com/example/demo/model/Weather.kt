package com.example.demo.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class Weather: JsonModel {

    val idProperty = SimpleIntegerProperty()
    var id: Int by idProperty

    val mainProperty = SimpleStringProperty()
    var main: String by mainProperty

    val descriptionProperty = SimpleStringProperty()
    var description: String by descriptionProperty

    val iconProperty = SimpleStringProperty()
    var icon: String by iconProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            id = getInt("id")
            main = getString("main")
            description = getString("description")
            icon = getString("icon")
        }
    }
}

class WeatherModel: ItemViewModel<Weather>() {
    val id = bind(Weather::idProperty)
    val main = bind(Weather::mainProperty)
    val description = bind(Weather::descriptionProperty)
    val icon = bind(Weather::iconProperty)
}
