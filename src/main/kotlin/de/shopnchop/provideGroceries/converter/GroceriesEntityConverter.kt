package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.GroceriesEntity
import org.springframework.stereotype.Component

@Component
class GroceriesEntityConverter {
    fun domainToEntity(groceries: Groceries): GroceriesEntity {
        return GroceriesEntity(
            groceries.name,
            groceries.amount,
            groceries.expirationDate
        )
    }

    fun entityToDomain(entity: GroceriesEntity): Groceries {
        return Groceries(
            entity.name,
            entity.amount,
            entity.expirationDate
        )
    }
}