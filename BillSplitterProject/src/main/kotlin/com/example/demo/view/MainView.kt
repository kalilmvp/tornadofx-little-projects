package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.MainController
import javafx.beans.binding.Bindings
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.ButtonType
import javafx.scene.control.ComboBox
import javafx.scene.control.Slider
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val mainController: MainController by inject()
    var splitCombo: ComboBox<Int> by singleAssign()
    var mSlider: Slider by singleAssign()
    var billAmountField: TextField by singleAssign()

    override val root = vbox {
        alignment = Pos.TOP_CENTER
        addClass(Styles.vbox)

        label("Total per Person") {
            addClass(Styles.heading)
        }
        label {
            addClass(Styles.heading)
            this.textProperty().bind(Bindings.concat("$ ",
                    Bindings.format("%.2f", mainController.totalPerPerson)))
        }

        form {
            fieldset(labelPosition = Orientation.HORIZONTAL) {
                vbox(spacing = 5) {
                    field {
                        hbox(12) {
                            label("Bill Amount:")
                            maxWidth = 250.0
                            billAmountField = textfield()
                            billAmountField.filterInput {
                                it.controlNewText.isDouble() || it.controlNewText.isInt()
                            }
                            billAmountField.setOnKeyPressed {
                                if (it.code == KeyCode.ENTER)
                                    validateField()
                            }
                        }
                    }
                    field {
                        hbox(30) {
                            label("Split By:")

                            splitCombo = combobox(values = listOf(1,2,3,4,5)) {
                                prefWidth = 135.0
                                value = 1
                            }
                            splitCombo.valueProperty().onChange {
                                validateField()
                            }
                        }
                    }
                    field {
                        hbox(spacing = 23.0) {
                            label("Total Tip")
                            label().textProperty().bind(Bindings.concat("$",
                                    Bindings.format("%.2f", mainController.tipPercentageAmount)))
                        }
                    }
                    field {
                        hbox {
                            hbox(spacing = 35) {
                                label("Tip %:")

                                mSlider = slider(min = 0, max = 100, orientation = Orientation.HORIZONTAL)
                                mSlider.valueProperty().onChange {
                                    validateField()
                                }
                            }
                            label().textProperty().bind(Bindings.concat(
                                    mainController.sliderPercentageAmount, " %"
                            ))
                        }
                    }
                }

            }
        }

    }

    private fun validateField() {
        if (!billAmountField.text.toString().isEmpty()) {
            mainController.calculate(
                    SimpleDoubleProperty(billAmountField.text.toDouble()),
                    SimpleIntegerProperty(splitCombo.value),
                    SimpleIntegerProperty(mSlider.value.toInt()))
        } else {
            error("Error", "Empty field not allowed",
                    buttons = *arrayOf(ButtonType.OK))
        }
    }
}
