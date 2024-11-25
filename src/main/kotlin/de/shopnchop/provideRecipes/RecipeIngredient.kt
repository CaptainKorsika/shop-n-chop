package de.shopnchop.provideRecipes

data class RecipeIngredient(
    val name: String,
    var amount: Double,
    val unit: String,
)