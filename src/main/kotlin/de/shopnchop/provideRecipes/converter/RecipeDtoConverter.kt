package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.shared.converter.IngredientConverter
import de.shopnchop.shared.dto.IngredientDTO
import de.shopnchop.shared.dto.RecipeDTO
import org.springframework.stereotype.Component

@Component
class RecipeDtoConverter(val ingredientConverter: IngredientConverter) {
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