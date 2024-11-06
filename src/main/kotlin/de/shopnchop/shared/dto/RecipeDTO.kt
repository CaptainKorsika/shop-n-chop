package de.shopnchop.shared.dto

data class RecipeDTO(
    val id: String,
    val name: String,
    val manual: String,
    val duration: String,
    val ingredients: List<IngredientDTO>
)