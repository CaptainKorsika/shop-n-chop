package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideRecipes.RecipeIngredient
import de.shopnchop.provideRecipes.RecipeIngredientEntity
import org.springframework.stereotype.Component

@Component
class RecipeIngredientEntityConverter {

    fun domainToEntity(recipeIngredient: RecipeIngredient): RecipeIngredientEntity {
        return RecipeIngredientEntity(
            recipeIngredient.name,
            recipeIngredient.amount,
            recipeIngredient.unit
        )
    }

    fun entityToDomain(entity: RecipeIngredientEntity): RecipeIngredient {
        return RecipeIngredient(
            entity.name,
            entity.amount,
            entity.unit
        )
    }

}