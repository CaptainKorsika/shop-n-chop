package de.shopnchop.provideRecipes


data class Recipe(
    val id: String?,
    val name: String,
    val manual: String,
    val duration: String,
    val ingredients: List<RecipeIngredient>
)