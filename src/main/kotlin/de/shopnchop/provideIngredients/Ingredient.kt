package de.shopnchop.provideIngredients

data class Ingredient(
    val name: String,
    val amount: String,
    val unit: String,
    val category: String,
    val durability: String
)