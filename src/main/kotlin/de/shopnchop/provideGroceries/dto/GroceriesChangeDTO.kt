package de.shopnchop.provideGroceries.dto

data class GroceriesChangeDTO(
    val id: String?,
    val name: String,
    val amount: String,
    val currentExpirationDate: String,
    val newExpirationDate: String
)
