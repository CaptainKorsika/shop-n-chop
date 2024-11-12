package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideIngredients.converter.IngredientEntityConverter
import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideRecipes.RecipeEntity
import org.springframework.stereotype.Component

@Component
class RecipeEntityConverter(
    private val ingredientEntityConverter: RecipeIngredientEntityConverter) {

    fun domainToEntity(recipe: Recipe): RecipeEntity {

        val entityList = recipe.ingredients.map { ingredientEntityConverter.domainToEntity(it) }
        return RecipeEntity(
            recipe.id,
            recipe.name,
            recipe.manual,
            recipe.duration,
            entityList
        )
    }


    fun entityToDomain(entity: RecipeEntity): Recipe {

        val ingredientList = entity.ingredients.map { ingredientEntityConverter.entityToDomain(it) }

        return Recipe(
            entity.id,
            entity.name,
            entity.manual,
            entity.duration,
            ingredientList
        )
    }

}