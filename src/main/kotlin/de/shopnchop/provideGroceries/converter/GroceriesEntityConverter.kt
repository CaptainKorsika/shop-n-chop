package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.GroceriesEntity
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesEntityConverter {
    fun domainToEntity(groceries: Groceries): GroceriesEntity {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate = formatter.format(groceries.expirationDate)

        var amountString = groceries.amount.toString()
        if (amountString.endsWith(".0")) {
            amountString = amountString.substring(0, amountString.length - 2)
        }

        return GroceriesEntity(
            groceries.id,
            groceries.name,
            amountString,
            formattedDate
        )
    }

    fun entityToDomain(entity: GroceriesEntity): Groceries {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date: Date = dateFormat.parse(entity.expirationDate)

        return Groceries(
            entity.id,
            entity.name,
            entity.amount.toDouble(),
            date
        )
    }
}