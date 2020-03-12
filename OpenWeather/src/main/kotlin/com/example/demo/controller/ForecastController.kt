package com.example.demo.controller

import com.example.demo.model.CityModel
import com.example.demo.model.ForecastPaload
import com.example.demo.util.APP_ID
import javafx.collections.FXCollections
import tornadofx.*
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.SocketAddress
import java.text.SimpleDateFormat
import java.util.*

class ForecastController : Controller() {

    val selectedCity: CityModel by inject()

    var allWeather = FXCollections.emptyObservableList<ForecastPaload>()

    val api: Rest by inject()

    init {
        api.baseURI = "https://api.openweathermap.org/data/2.5/forecast/"
        api.proxy = Proxy(
                Proxy.Type.HTTP,
                InetSocketAddress("proxy.jupiter.co.ao", 3128))
    }

    fun listPayload(city: String = selectedCity.name.value) =
            api.get("?q=$city&appid=$APP_ID&units=imperial")
                    .list().toModel<ForecastPaload>()

    fun getIcon(iconString: String) = when (iconString) {
        "Rain" -> "rain"
        "Clouds" -> "clouds"
        "Snow" -> "snow"
        "Clear" -> "clear"
        "Drizzle" -> "rain"
        "Fog" -> "fog"
        else -> "clear"
    }

    fun getFormattedDate(date: Long): String = SimpleDateFormat("EEE, d MMM yyyy")
            .format(Date(date * 1000))

}
