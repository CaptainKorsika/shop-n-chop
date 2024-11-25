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

        if (groceries.expirationDate != null) {
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            formattedDate = formatter.format(groceries.expirationDate)
        }

        var amountString = groceries.amount.toString()
        if (amountString.endsWith(".0")) {
            amountString = amountString.substring(0, amountString.length - 2)
        }

        return GroceriesDTO(
            groceries.name,
            amountString,
            formattedDate,
        )
    }

    fun dtoToDomain(dto: GroceriesDTO): Groceries {
        var date: Date? = null

        if (dto.expirationDate != null) {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            date = dateFormat.parse(dto.expirationDate)
        }

        return Groceries(
            dto.name,
            dto.amount.toDouble(),
            date
        )
    }
}