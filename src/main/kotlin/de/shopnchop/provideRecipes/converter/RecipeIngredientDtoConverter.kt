package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideRecipes.dto.RecipeIngredientDTO
import de.shopnchop.provideRecipes.RecipeIngredient
import org.springframework.stereotype.Component

@Component
class RecipeIngredientDtoConverter {

    fun domainToDto(ingredient: RecipeIngredient): RecipeIngredientDTO {
        return RecipeIngredientDTO(
            ingredient.name,
            ingredient.amount.toString(),
            ingredient.unit,
        )
    }

    fun dtoToDomain(dto: RecipeIngredientDTO): RecipeIngredient {
        return RecipeIngredient(
            dto.name,
            dto.amount.toDouble(),
            dto.unit)
    }


}