package de.shopnchop.provideRecipes

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recipe")
data class RecipeEntity(
    @Id
    val id: String? = null,
    val name: String,
    val manual: String,
    val duration: String,
    val ingredients: List<RecipeIngredientEntity>,
)