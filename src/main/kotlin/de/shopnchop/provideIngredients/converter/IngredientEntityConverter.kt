package de.shopnchop.provideIngredients.converter

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideIngredients.IngredientEntity
import org.springframework.stereotype.Component

@Component
class IngredientEntityConverter {

    fun domainToEntity(ingredient: Ingredient): IngredientEntity {
        return IngredientEntity(
            ingredient.name,
            ingredient.amount,
            ingredient.unit,
            ingredient.category,
            ingredient.durability
        )
    }

    fun entityToDomain(entity: IngredientEntity): Ingredient {
        return Ingredient(
            entity.name,
            entity.amount,
            entity.unit,
            entity.category,
            entity.durability
        )
    }
}