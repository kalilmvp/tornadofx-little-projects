package com.example.demo.model

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*
import javax.json.JsonObject

class Wind: JsonModel {

    val speedProperty = SimpleDoubleProperty()
    var speed: Double by speedProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            speed = getDouble("speed")
        }
    }
}

class WindModel: ItemViewModel<Wind>() {
    val speed = bind(Wind::speed)
}
