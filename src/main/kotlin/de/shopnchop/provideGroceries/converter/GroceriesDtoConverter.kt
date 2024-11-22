package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.dto.GroceriesDTO
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesDtoConverter {
    fun domainToDTO(groceries: Groceries): GroceriesDTO {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate = formatter.format(groceries.expirationDate)

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
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date: Date = dateFormat.parse(dto.expirationDate)


        return Groceries(
            dto.name,
            dto.amount.toDouble(),
            date
        )
    }
}