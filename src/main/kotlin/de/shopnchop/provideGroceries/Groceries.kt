package de.shopnchop.provideGroceries

import java.util.Date

data class Groceries(
    val id: String?,
    val name: String,
    val amount: Double,
    val currentExpirationDate: Date?,
    val newExpirationDate: Date? = null
)