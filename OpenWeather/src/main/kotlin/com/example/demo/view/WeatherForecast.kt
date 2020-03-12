package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.ForecastController
import com.example.demo.model.ForecastPaload
import com.example.demo.model.Lista
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.binding.Bindings
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.FontPosture
import tornadofx.*

class WeatherForecast : View("Weather Forecast") {
    val controller: ForecastController by inject()
    var forecastPayload = ForecastPaload()

    var cityLabel: Label by singleAssign()
    var todayTemp: Label by singleAssign()
    var todayIcon: Label by singleAssign()
    var forecastView: DataGrid<Lista> by singleAssign()
    var sevenDayLabel: Label by singleAssign()
    var hboxDivider: HBox by singleAssign()

    override val root = borderpane {
        style {
            backgroundColor += c("#666699")
        }
        center = vbox {
            addClass(Styles.contentWrapper)
            currentWeatherView()
            vbox(alignment = Pos.CENTER, spacing = 15) {
                cityLabel  = label()
                todayIcon = label()
                todayTemp = label()
                hboxDivider = hbox()
                sevenDayLabel = label()
                forecastView = datagrid()
            }
        }
    }

    private fun VBox.currentWeatherView() = vbox {
        form {
            paddingAll = 20.0
            fieldset {
                field("Enter city", Orientation.VERTICAL) {
                    textfield(controller.selectedCity.name) {
                        this.required()
                        validator {
                            if (it.isNullOrBlank()) error("The field cannot be empty")
                            else null
                        }

                        setOnKeyPressed {
                            if (it.code == KeyCode.ENTER) {
                                controller.selectedCity.commit {
                                    runAsync {
                                        controller.allWeather = controller.listPayload(
                                                city = controller.selectedCity.name.value)
                                    } ui {
                                        forecastPayload = controller.allWeather[0]

                                        vbox {
                                            cityLabel.text = forecastPayload.city.name + ", " +
                                                    forecastPayload.city.country + " - " +
                                                    controller.getFormattedDate(forecastPayload.lista[0].dt.toLong())
                                            cityLabel.apply {
                                                addClass(Styles.mainLabels)
                                            }

                                            todayIcon.graphic = imageview("/${controller.getIcon(forecastPayload.lista[0].weather[0].main)}.png", lazyload = true) {
                                                fitHeight = 100.0
                                                fitWidth = 100.0
                                            }

                                            todayTemp.text = "${forecastPayload.lista[0].main.temp} F " +
                                                    forecastPayload.lista[0].weather[0].description
                                            todayTemp.apply {
                                                addClass(Styles.mainLabels)
                                            }

                                            hboxDivider.style {
                                                borderColor += box(Color.TRANSPARENT,
                                                        Color.TRANSPARENT,
                                                        Color.GRAY,
                                                        Color.TRANSPARENT)
                                            }

                                            sevenDayLabel.text = "7-Day Weather Forecast"
                                            sevenDayLabel.style {
                                                fill = Color.GREY
                                                fontStyle = FontPosture.ITALIC
                                                opacity = 0.7
                                            }

                                            val forecast7 = forecastPayload.lista.slice(1..7)

                                            forecastView.items = forecast7.observable()
                                            forecastView.apply {
                                                cellWidth = 120.0
                                                cellHeight = 200.0
                                                cellCache {
                                                    vbox(spacing = 10) {
                                                        vbox(alignment = Pos.CENTER) {
                                                            label(
                                                                    controller.getFormattedDate(it.dtPropety.value.toLong()).split(",")[0]
                                                            )
                                                            label {
                                                                graphic = imageview("/${controller.getIcon(it.weather[0].main)}.png", lazyload = true) {
                                                                    fitHeight = 100.0
                                                                    fitWidth = 100.0
                                                                }
                                                            }
                                                        }

                                                        vbox(alignment = Pos.CENTER) {
                                                            label(it.main.tempMinProperty).apply {
                                                                graphic = FontAwesomeIconView(FontAwesomeIcon.LONG_ARROW_DOWN)
                                                            }
                                                            label(it.main.tempMaxProperty).apply {
                                                                graphic = FontAwesomeIconView(FontAwesomeIcon.LONG_ARROW_UP)
                                                            }
                                                            label {
                                                                this.textProperty().bind(Bindings.concat("Hum: ", it.main.humidityProperty, "%"))
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
