package de.shopnchop.provideIngredients.dto

data class IngredientDTO(
    val name: String,
    val amount: String,
    val unit: String,
    val category: String,
    val durability: String
)