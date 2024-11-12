package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideIngredients.dto.RecipeIngredientDTO
import de.shopnchop.provideRecipes.RecipeIngredient
import org.springframework.stereotype.Component

@Component
class RecipeIngredientDtoConverter {

    fun domainToDto(ingredient: RecipeIngredient): RecipeIngredientDTO {
        return RecipeIngredientDTO(
            ingredient.name,
            ingredient.amount,
            ingredient.unit,
        )
    }

    fun dtoToDomain(dto: RecipeIngredientDTO): RecipeIngredient {
        return RecipeIngredient(
            dto.name,
            dto.amount,
            dto.unit)
    }


}