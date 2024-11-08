package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideIngredients.converter.IngredientDtoConverter
import de.shopnchop.provideIngredients.dto.IngredientDTO
import de.shopnchop.provideRecipes.dto.RecipeDTO
import org.springframework.stereotype.Component

@Component
class RecipeDtoConverter(val ingredientConverter: IngredientDtoConverter) {
    fun domainToDTO(recipe: Recipe): RecipeDTO {

        val ingredientsList = recipe.ingredients
        val ingredientDTOList: List<IngredientDTO> = ingredientsList.map { ingredientConverter.domainToDTO(it) }
        return RecipeDTO(recipe.id, recipe.name, recipe.manual, recipe.duration, ingredientDTOList)
    }

    fun dtoToDomain(dto: RecipeDTO): Recipe {

        val ingredientsList = dto.ingredients
        val ingredientList: List<Ingredient> = ingredientsList.map { ingredientConverter.dtoToDomain(it) }
        return Recipe(dto.id, dto.name, dto.manual, dto.duration, ingredientList)
    }
}