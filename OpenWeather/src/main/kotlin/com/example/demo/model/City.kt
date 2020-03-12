package com.example.demo.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

//https://api.openweathermap.org/data/2.5/forecast?q=london&appid=2ed4ab86a2383a2e9a8f83bb30240c47
class City: JsonModel {

    val idProperty = SimpleIntegerProperty()
    var id: Int by idProperty

    val nameProperty = SimpleStringProperty()
    var name: String by nameProperty

    val countryProperty = SimpleStringProperty()
    var country: String by countryProperty

    val populationProperty = SimpleIntegerProperty()
    var population: Int by populationProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            id = getInt("id")
            name = getString("name")
            country = getString("country")
            population = getInt("population")
        }
    }

    override fun toString() = name
}

class CityModel: ItemViewModel<City>() {
    val id = bind(City::idProperty)
    val name = bind(City::nameProperty)
    val country = bind(City::countryProperty)
    val population = bind(City::populationProperty)
}
