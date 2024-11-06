package de.shopnchop.shared.converter

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.shared.dto.IngredientDTO
import org.springframework.stereotype.Component

@Component
class IngredientConverter {
    fun domainToDTO(ingredient: Ingredient): IngredientDTO {
        return IngredientDTO(ingredient.name, ingredient.amount, ingredient.unit, ingredient.category, ingredient.durability)
    }

    fun dtoToDomain(dto: IngredientDTO): Ingredient {
        return Ingredient(dto.name, dto.amount, dto.unit, dto.category, dto.durability)
    }
}