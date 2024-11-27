package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.dto.GroceriesDTO
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesDtoConverter {
    fun domainToDTO(groceries: Groceries): GroceriesDTO {
        var formattedExpirationDate: String? = null

        if (groceries.currentExpirationDate != null) {
            formattedExpirationDate = this.convertDateToString(groceries.currentExpirationDate!!)
        }

        val formattedPurchaseDate = this.convertDateToString(groceries.purchaseDate)

        var amountString = groceries.amount.toString()
        if (amountString.endsWith(".0")) {
            amountString = amountString.substring(0, amountString.length - 2)
        }

        return GroceriesDTO(
            groceries.id,
            groceries.name,
            amountString,
            formattedPurchaseDate,
            formattedExpirationDate
        )
    }

    fun dtoToDomain(dto: GroceriesDTO): Groceries {
        var currentDate: Date? = null
        var newDate: Date? = null

        val purchaseDate = this.convertStringToDate(dto.purchaseDate)

        if (dto.currentExpirationDate != null) {
            currentDate = this.convertStringToDate(dto.currentExpirationDate)
        }

        if (dto.newExpirationDate != null) {
            newDate = this.convertStringToDate(dto.newExpirationDate)
        }

        return Groceries(
            dto.id,
            dto.name,
            dto.amount.toDouble(),
            purchaseDate,
            currentDate,
            newDate
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