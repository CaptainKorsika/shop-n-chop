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

        var amountString = groceries.amount.toString()
        if (amountString.endsWith(".0")) {
            amountString = amountString.substring(0, amountString.length - 2)
        }

        return GroceriesDTO(
            groceries.id,
            groceries.name,
            amountString,
            groceries.unit,
            formattedExpirationDate
        )
    }

    fun dtoToDomain(dto: GroceriesDTO): Groceries {
        var currentDate: Date? = null

        if (dto.currentExpirationDate != null) {
            currentDate = this.convertStringToDate(dto.currentExpirationDate)
        }

        return Groceries(
            dto.id,
            dto.name,
            dto.amount.toDouble(),
            dto.unit,
            currentDate
        )
    }

    private fun convertStringToDate(dateString: String?): Date? {
        if (dateString == null) {
            return null
        }

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.parse(dateString)
    }

    private fun convertDateToString(date: Date?): String? {
        if (date == null) {
            return null
        }

        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return formatter.format(date)
    }

}