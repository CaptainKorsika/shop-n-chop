package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.dto.GroceriesChangeDTO
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesChangeDtoConverter {

    fun dtoToDomain(dto: GroceriesChangeDTO): Groceries {

        val currentExpirationDate = convertStringToDate(dto.currentExpirationDate)
        val newExpirationDate = convertStringToDate(dto.newExpirationDate)


        return Groceries(
            dto.id,
            dto.name,
            dto.amount.toDouble(),
            currentExpirationDate,
            newExpirationDate = newExpirationDate
        )
    }

    private fun convertStringToDate(dateString: String?): Date? {
        if (dateString == null) {
            return null
        }

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.parse(dateString)
    }
}