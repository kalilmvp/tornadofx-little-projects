package com.example.demo.model

import com.example.demo.util.toJavaLocalDate
import javafx.beans.binding.Bindings
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import java.time.LocalDate
import tornadofx.*

fun ResultRow.toExpensesEntry() = ExpensesEntry(
        this[ExpensesEntryTbl.id],
        this[ExpensesEntryTbl.name],
        this[ExpensesEntryTbl.entryDate].toJavaLocalDate(),
        this[ExpensesEntryTbl.price].toDouble()
)

object ExpensesEntryTbl: Table("expense_entry") {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", length = 50)
    val entryDate = date("entry_date")
    val price = decimal("price", scale = 2, precision = 9)
}

class ExpensesEntry(id: Int, name: String, entryDate: LocalDate, price: Double) {
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val entryDateProperty = SimpleObjectProperty(entryDate)
    var entryDate by entryDateProperty

    val priceProperty = SimpleDoubleProperty(price)
    var price by priceProperty

    val totalExpenses = Bindings.add(priceProperty, 0)

    override fun toString(): String {
        return "ExpensesEntry(id=$id, name=$name, entryDate=$entryDate, price=$price)"
    }
}

class ExpensesEntryModel: ItemViewModel<ExpensesEntry>() {
    val id = bind { item?.idProperty}
    val name = bind { item?.nameProperty }
    val entryDate = bind { item?.entryDateProperty }
    val price = bind { item?.priceProperty }
    val totalExpenses = itemProperty.select(ExpensesEntry::totalExpenses)
}
