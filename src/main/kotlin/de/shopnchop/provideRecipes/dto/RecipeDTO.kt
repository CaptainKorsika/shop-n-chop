package de.shopnchop.provideRecipes.dto

import de.shopnchop.provideIngredients.dto.IngredientDTO

data class RecipeDTO(
    val id: String,
    val name: String,
    val manual: String,
    val duration: String,
    val ingredients: List<IngredientDTO>
)