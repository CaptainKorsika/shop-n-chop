package de.shopnchop.provideIngredients.converter

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideIngredients.IngredientEntity
import org.springframework.stereotype.Component

@Component
class IngredientEntityConverter {

    fun domainToEntity(ingredient: Ingredient): IngredientEntity {
        return IngredientEntity(
            ingredient.name,
            ingredient.unit,
            ingredient.category,
            ingredient.durability.toString()
        )
    }

    fun entityToDomain(entity: IngredientEntity): Ingredient {
        return Ingredient(
            entity.name,
            entity.unit,
            entity.category,
            entity.durability.toInt()
        )
    }
}