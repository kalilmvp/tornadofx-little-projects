package com.example.demo.view

import com.example.demo.controller.ItemController
import com.example.demo.model.ExpensesEntryTbl
import com.example.demo.util.createTables
import com.example.demo.util.enableConsoleLogger
import com.example.demo.util.execute
import com.example.demo.util.toDate
import javafx.scene.control.TabPane
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import tornadofx.*
import java.math.BigDecimal
import java.time.LocalDate

class BudgetTrackerWorkspace : Workspace("Budget Tracker Workspace", NavigationMode.Tabs) {

    init {
        // initialize db...
        enableConsoleLogger()
        Database.connect("jdbc:sqlite:./app-budget-tracker.db", "org.sqlite.JDBC")
        createTables()

        // controllers
        ItemController()

        // dock our views
        dock<ExpensesEditor>()

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }
}
