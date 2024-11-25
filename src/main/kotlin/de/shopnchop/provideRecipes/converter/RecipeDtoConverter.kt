package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideRecipes.dto.RecipeIngredientDTO
import de.shopnchop.provideRecipes.RecipeIngredient
import de.shopnchop.provideRecipes.dto.RecipeDTO
import org.springframework.stereotype.Component

@Component
class RecipeDtoConverter(val ingredientDtoConverter: RecipeIngredientDtoConverter) {
    fun domainToDTO(recipe: Recipe): RecipeDTO {

        val ingredientsList = recipe.ingredients
        val ingredientDTOList: List<RecipeIngredientDTO> = ingredientsList.map { ingredientDtoConverter.domainToDto(it) }
        return RecipeDTO(recipe.id, recipe.name, recipe.manual, recipe.duration, ingredientDTOList)
    }

    fun dtoToDomain(dto: RecipeDTO): Recipe {

        val ingredientsList = dto.ingredients
        val ingredientList: List<RecipeIngredient> = ingredientsList.map { ingredientDtoConverter.dtoToDomain(it) }
        return Recipe(dto.id, dto.name, dto.manual, dto.duration, ingredientList)
    }
}