package de.shopnchop.provideGroceries.dto

data class GroceriesDTO(
    val id: String?,
    val name: String,
    val amount: String,
    val unit: String,
    val currentExpirationDate: String? = null
)
