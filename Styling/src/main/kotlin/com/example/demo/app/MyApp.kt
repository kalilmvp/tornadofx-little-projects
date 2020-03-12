package com.example.demo.app

import com.example.demo.view.MainView
import javafx.stage.Stage
import tornadofx.*

class MyApp: App(MainView::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
        reloadViewsOnFocus()
    }
    override fun start(stage: Stage) {
        with(stage) {
            width = 700.0
            height = 700.0
        }
        super.start(stage)
    }
}
