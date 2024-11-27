package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.dto.GroceriesPurchaseDTO
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesPurchaseDtoConverter {
    fun dtoToDomain(dto: GroceriesPurchaseDTO): Groceries {

        val purchaseDate = convertStringToDate(dto.purchaseDate)

        return Groceries(
            null,
            dto.name,
            dto.amount.toDouble(),
            null,
            purchaseDate
        )
    }

    private fun convertStringToDate(dateString: String): Date {

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.parse(dateString)
    }

}