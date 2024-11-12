package de.shopnchop.provideRecipes.dto

import de.shopnchop.provideIngredients.dto.RecipeIngredientDTO

data class RecipeDTO(
    val id: String,
    val name: String,
    val manual: String,
    val duration: String,
    val ingredients: List<RecipeIngredientDTO>
)