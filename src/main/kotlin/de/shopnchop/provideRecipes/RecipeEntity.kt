package de.shopnchop.provideRecipes

import de.shopnchop.provideIngredients.IngredientEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recipe")
data class RecipeEntity(
    @Id val id: String,
    val name: String,
    val manual: String,
    val duration: String,
    val ingredients: List<IngredientEntity>,
)