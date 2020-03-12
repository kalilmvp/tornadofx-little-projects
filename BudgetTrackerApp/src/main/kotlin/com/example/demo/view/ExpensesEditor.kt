package com.example.demo.view

import com.example.demo.controller.ItemController
import com.example.demo.model.ExpensesEntryModel
import javafx.beans.binding.Bindings
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class ExpensesEditor : View("Expense Editor") {
    val model = ExpensesEntryModel()
    private val controller: ItemController by inject()

    private var mTableView: TableViewEditModel<ExpensesEntryModel> by singleAssign()
    private val totalExpensesProperty = SimpleDoubleProperty(0.0)
    private var totalExpensesLabel: Label by singleAssign()

    init {
        updateTotalExpenses()
    }

    override val root = borderpane {
        disableDelete()
        disableSave()
        disableCreate()
        disableRefresh()
        disableClose()

        center = vbox {
            form {
                fieldset {
                    field("Entry Date") {
                        maxWidth = 220.0
                        datepicker(model.entryDate) {
                            this.required()
                            validator {
                                when {
                                    it?.dayOfMonth.toString().isEmpty() ||
                                            it?.monthValue.toString().isEmpty() ||
                                            it?.year.toString().isEmpty() ->
                                        error("The date cannot be empty")
                                    else -> null
                                }
                            }
                        }
                    }
                }
                fieldset {
                    field("Name") {
                        maxWidth = 220.0
                        textfield(model.name) {
                            this.required()
                            validator {
                                when {
                                    it.isNullOrEmpty() -> error("Name cannot be empty")
                                    it!!.length < 3 -> error("Name too short")
                                    else -> null
                                }
                            }
                        }
                    }
                }
                fieldset {
                    field("Price") {
                        maxWidth = 220.0
                        textfield(model.price) {
                            filterInput {
                                it.controlNewText.isDouble() || it.controlNewText.isInt()
                            }
                            this.required()
                            validator {
                                when(it) {
                                    null -> error("Price cannot be empty")
                                    else -> null
                                }
                            }

                            setOnKeyPressed {
                                if (it.code == KeyCode.ENTER) {
                                    model.commit {
                                        addItem()
                                    }
                                }
                            }
                        }
                    }
                }

                hbox(spacing = 10) {
                    button("Add Item") {
                        enableWhen(model.valid)
                        action {
                            model.commit {
                                addItem()
                            }
                        }
                    }

                    button("Delete Item") {
                        action {
                            val selectedItem = mTableView.tableView.selectedItem
                            when(selectedItem) {
                                null -> return@action
                                else -> {
                                    controller.delete(selectedItem)

                                    updateTotalExpenses()
                                }
                            }
                        }
                    }

                    button("Reset") {
                        enableWhen(model.valid)
                        action {
                            model.commit {
                                model.rollback()
                            }
                        }
                    }
                }
            }

            tableview<ExpensesEntryModel> {
                mTableView = editModel
                items = controller.items
                column("ID", ExpensesEntryModel::id)
                column("NAME", ExpensesEntryModel::name).makeEditable()
                column("DATE", ExpensesEntryModel::entryDate).makeEditable()
                column("PRICE", ExpensesEntryModel::price).makeEditable()

                onEditCommit {
                    controller.update(it)
                    updateTotalExpenses()
                }
            }
        }
        right = vbox(10) {
            alignment = Pos.CENTER
            paddingBottom = 10

            piechart {
                data = controller.pieItemsData
            }

            totalExpensesLabel = label {
                visibleWhen { booleanBinding(controller.pieItemsData) { isNotEmpty() } }

                style {
                    fontSize = 19.px
                    padding = box(10.px)
                    textFill = Color.GREEN
                    fontWeight = FontWeight.EXTRA_BOLD
                    borderRadius += box(8.px)
                    backgroundColor += c("white", 0.8)
                }
                bind(Bindings.concat("Total Expenses: ", "$", Bindings.format("%.2f", totalExpensesProperty)))
            }
        }
    }

    private fun addItem() {
        controller.add(
                model.name.value,
                model.entryDate.value,
                model.price.value.toDouble())

        model.rollback()

        updateTotalExpenses()
    }

    private fun updateTotalExpenses() {
        var total = 0.0

        try {
            controller.items.forEach {
                total += it.price.value.toDouble()
            }
            totalExpensesProperty.set(total)
            model.totalExpenses.value = total
        } catch (e: Exception) {
            totalExpensesProperty.set(0.0)
        }
    }
}
