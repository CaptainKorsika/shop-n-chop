package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.dto.GroceriesDTO
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesDtoConverter {
    fun domainToDTO(groceries: Groceries): GroceriesDTO {
        var formattedDate: String? = null

        if (groceries.currentExpirationDate != null) {
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            formattedDate = formatter.format(groceries.currentExpirationDate)
        }

        var amountString = groceries.amount.toString()
        if (amountString.endsWith(".0")) {
            amountString = amountString.substring(0, amountString.length - 2)
        }

        return GroceriesDTO(
            groceries.id,
            groceries.name,
            amountString,
            formattedDate
        )
    }

    fun dtoToDomain(dto: GroceriesDTO): Groceries {
        var currentDate: Date? = null
        var newDate: Date? = null

        if (dto.currentExpirationDate != null) {
            currentDate = convertDate(dto.currentExpirationDate)
        }

        if (dto.newExpirationDate != null) {
            newDate = convertDate(dto.newExpirationDate)
        }

        return Groceries(
            dto.id,
            dto.name,
            dto.amount.toDouble(),
            currentDate,
            newDate
        )
    }

    private fun convertDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.parse(dateString)
    }

}