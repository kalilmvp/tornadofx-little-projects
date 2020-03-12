package com.example.demo.model

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*
import javax.json.JsonObject

class Main: JsonModel {

    val tempProperty = SimpleDoubleProperty()
    var temp: Double by tempProperty

    val feelsLikeProperty = SimpleDoubleProperty()
    var feelsLike: Double by feelsLikeProperty

    val tempMinProperty = SimpleDoubleProperty()
    var tempMin: Double by tempMinProperty

    val tempMaxProperty = SimpleDoubleProperty()
    var tempMax: Double by tempMaxProperty

    val humidityProperty = SimpleIntegerProperty()
    var humidity: Int by humidityProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            temp = getDouble("temp")
            feelsLike = getDouble("feels_like")
            tempMin = getDouble("temp_min")
            tempMax = getDouble("temp_max")
            humidity = getInt("humidity")
        }
    }
}

class MainModel: ItemViewModel<Main>() {
    val temp = bind(Main::tempProperty)
    val feelsLike = bind(Main::feelsLikeProperty)
    val tempMin = bind(Main::tempMinProperty)
    val tempMax = bind(Main::tempMaxProperty)
    val humidity = bind(Main::humidityProperty)
}
