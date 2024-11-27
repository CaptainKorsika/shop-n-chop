package de.shopnchop.provideGroceries.dto

data class GroceriesDTO(
    val id: String?,
    val name: String,
    val amount: String,
    val purchaseDate: String,
    val currentExpirationDate: String? = null,
    val newExpirationDate: String? = null
)
