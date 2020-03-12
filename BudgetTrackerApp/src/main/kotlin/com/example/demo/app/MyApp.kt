package com.example.demo.app

import com.example.demo.view.BudgetTrackerWorkspace
import javafx.stage.Stage
import tornadofx.*

class MyApp: App(BudgetTrackerWorkspace::class, Styles::class) {
    init {
        /*reloadStylesheetsOnFocus()
        reloadViewsOnFocus()*/
    }
    override fun start(stage: Stage) {
        with(stage) {
            width = 1200.0
            height = 600.0
        }
        super.start(stage)
    }
}
