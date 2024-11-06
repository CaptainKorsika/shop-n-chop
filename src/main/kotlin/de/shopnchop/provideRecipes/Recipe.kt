package de.shopnchop.provideRecipes

import de.shopnchop.provideIngredients.Ingredient

data class Recipe(
    val id: String,
    val name: String,
    val manual: String,
    val duration: String,
    val ingredients: List<Ingredient>
)