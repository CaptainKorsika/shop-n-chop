package de.shopnchop.provideGroceries.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.dto.GroceriesDTO
import org.springframework.stereotype.Component

@Component
class GroceriesDtoConverter {
    fun domainToDTO(groceries: Groceries): GroceriesDTO {
        return GroceriesDTO(
            groceries.name,
            groceries.amount,
            groceries.expirationDate
        )
    }

    fun dtoToDomain(dto: GroceriesDTO): Groceries {
        return Groceries(
            dto.name,
            dto.amount,
            dto.expirationDate
        )
    }
}