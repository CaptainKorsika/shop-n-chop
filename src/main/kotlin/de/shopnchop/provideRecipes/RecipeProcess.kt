package de.shopnchop.provideRecipes

import de.shopnchop.provideRecipes.converter.RecipeEntityConverter
import org.springframework.stereotype.Component

@Component
class RecipeProcess(val recipeRepository: RecipeRepository, val recipeEntityConverter: RecipeEntityConverter) {


    fun fetchRecipes(): List<Recipe> {
        val recipeList = mutableListOf<Recipe>()
        recipeRepository.findAll().map { recipeList.add(recipeEntityConverter.entityToDomain(it)) }.toList()

        return recipeList
    }

    fun fetchRecipeById(id: String): Recipe? {

        val recipeOrNull = recipeRepository.findById(id)
        if (recipeOrNull.isEmpty()) {
            return null
        }
        return recipeEntityConverter.entityToDomain(recipeOrNull.get())
    }

    fun saveRecipe(recipe: Recipe) {

        val recipeEntity = recipeEntityConverter.domainToEntity(recipe)
        recipeRepository.save(recipeEntity)

    }
}