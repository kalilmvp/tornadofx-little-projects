package com.example.demo.controller

import com.example.demo.model.ExpensesEntry
import com.example.demo.model.ExpensesEntryModel
import com.example.demo.model.ExpensesEntryTbl
import com.example.demo.model.toExpensesEntry
import com.example.demo.util.execute
import com.example.demo.util.toDate
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.chart.PieChart
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import tornadofx.*
import java.math.BigDecimal
import java.time.LocalDate

class ItemController: Controller() {

    // get all items
    private val listOfItems: ObservableList<ExpensesEntryModel> = execute {
        ExpensesEntryTbl.selectAll().map {
            ExpensesEntryModel().apply {
                item = it.toExpensesEntry()
            }
        }.observable()
    }

    var items: ObservableList<ExpensesEntryModel> by singleAssign()
    var pieItemsData = FXCollections.observableArrayList<PieChart.Data>()

    init {
        items = listOfItems

        items.forEach {
            pieItemsData.add(PieChart.Data(it.name.value, it.price.value.toDouble()))
        }
    }

    fun add(name: String, entryDate: LocalDate, price: Double): ExpensesEntry {
        val newEntry = execute {
            ExpensesEntryTbl.insert {
                it[ExpensesEntryTbl.name] = name
                it[ExpensesEntryTbl.entryDate] = entryDate.toDate()
                it[ExpensesEntryTbl.price] = BigDecimal.valueOf(price)
            }
        }

        val expenseEntry = ExpensesEntry(
                newEntry[ExpensesEntryTbl.id], name, entryDate, price)

        listOfItems.add(ExpensesEntryModel().apply {
            item = expenseEntry
        })

        pieItemsData.add(PieChart.Data(name, price))

        return expenseEntry
    }

    fun update(updatedItem: ExpensesEntryModel): Int {
        val updated = execute {
            ExpensesEntryTbl.update({
                ExpensesEntryTbl.id eq (updatedItem.id.value.toInt())
            }) {
                it[name] = updatedItem.name.value
                it[entryDate] = updatedItem.entryDate.value.toDate()
                it[price] = BigDecimal.valueOf(updatedItem.price.value.toDouble())
            }
        }

        updatePieChart(updatedItem)

        return updated
    }

    private fun updatePieChart(updatedItem: ExpensesEntryModel) {
        val modelId = updatedItem.id

        var currIndex = 0
        items.forEachIndexed { index, data ->
            if (modelId == data.id) {
                // the right object to update
                currIndex = index
                pieItemsData[currIndex].name = data.name.value
                pieItemsData[currIndex].pieValue = data.price.value.toDouble()
            }
        }
    }

    fun delete(model: ExpensesEntryModel) {
        execute {
            ExpensesEntryTbl.deleteWhere { ExpensesEntryTbl.id eq (model.id.value.toInt()) }
        }

        listOfItems.remove(model)

        removeModelFromPie(model)
    }

    private fun removeModelFromPie(model: ExpensesEntryModel) {
        var currIndex = 0
        pieItemsData.forEachIndexed { index, data ->
            if (data.name == model.name.value && index != -1) {
                currIndex = index
            }
        }
        pieItemsData.removeAt(currIndex)
    }
}
