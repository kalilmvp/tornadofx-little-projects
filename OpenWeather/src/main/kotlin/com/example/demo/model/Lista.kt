package com.example.demo.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import javax.json.JsonObject

class Lista: JsonModel {

    val dtPropety = SimpleIntegerProperty()
    var dt by dtPropety

    val mainProperty = SimpleObjectProperty<Main>()
    var main: Main by mainProperty

    val weatherProperty = SimpleListProperty<Weather>()
    var weather: List<Weather> by property(weatherProperty)

    val windProperty = SimpleObjectProperty<Wind>()
    var wind: Wind by windProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            dt = getInt("dt")
            main = getJsonObject("main").toModel()
            weather = getJsonArray("weather").toModel()
            wind = getJsonObject("wind").toModel()
        }
    }
}
