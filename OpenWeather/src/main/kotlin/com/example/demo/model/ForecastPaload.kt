package com.example.demo.model

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import javax.json.JsonObject

class ForecastPaload: JsonModel {

    val cityProperty = SimpleObjectProperty<City>()
    var city:City by cityProperty

    val listaProperty = SimpleListProperty<Lista>()
    var lista:List<Lista> by property(listaProperty)

    override fun updateModel(json: JsonObject) {
        with(json) {
            city = getJsonObject("city").toModel()
            lista = getJsonArray("list").toModel()
        }
    }
}
