package de.shopnchop.provideRecipes.dto

data class IngredientDTO(
    val id: String,
    val name: String,
    val amount: String,
    val unit: String,
    val category: String,
    val durability: String
)