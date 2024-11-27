package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.GroceriesEntity
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesEntityConverter {
    fun domainToEntity(groceries: Groceries): GroceriesEntity {
        var amountString = groceries.amount.toString()
        if (amountString.endsWith(".0")) {
            amountString = amountString.substring(0, amountString.length - 2)
        }

        val purchaseDate = this.convertDateToString(groceries.purchaseDate)
        val expirationDate = this.convertDateToString(groceries.currentExpirationDate!!)

        println(groceries.id)
        println(groceries.name)
        println(amountString)
        println(purchaseDate)
        println(expirationDate)



        return GroceriesEntity(
            groceries.id,
            groceries.name,
            amountString,
            purchaseDate,
            expirationDate
        )
    }

    fun entityToDomain(entity: GroceriesEntity): Groceries {
        val purchaseDate = this.convertStringToDate(entity.purchaseDate)
        val expirationDate = this.convertStringToDate(entity.expirationDate)


        return Groceries(
            entity.id,
            entity.name,
            entity.amount.toDouble(),
            purchaseDate,
            expirationDate
        )
    }

    private fun convertStringToDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.parse(dateString)
    }

    private fun convertDateToString(date: Date): String {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return formatter.format(date)
    }




}