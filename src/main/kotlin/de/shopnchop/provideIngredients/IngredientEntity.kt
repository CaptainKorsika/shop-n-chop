package de.shopnchop.provideIngredients

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ingredients")
data class IngredientEntity(
    @Id
    val name: String,
    val unit: String,
    val category: String,
    val durability: String,
)
