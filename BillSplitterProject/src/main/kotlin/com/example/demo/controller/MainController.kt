package com.example.demo.controller

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class MainController: Controller() {

    var totalPerPerson = SimpleDoubleProperty(0.0)
    var tipPercentageAmount = SimpleDoubleProperty(0.0)
    var sliderPercentageAmount = SimpleIntegerProperty(0)

    fun calculate(billAmountValue: SimpleDoubleProperty, splitByValue: SimpleIntegerProperty,
                  tipPercentageValue: SimpleIntegerProperty) {
        tipPercentageAmount.cleanBind((billAmountValue * tipPercentageValue) / 100)
        totalPerPerson.cleanBind((tipPercentageAmount + billAmountValue) / splitByValue)
        sliderPercentageAmount.cleanBind(tipPercentageValue)

    }
}
