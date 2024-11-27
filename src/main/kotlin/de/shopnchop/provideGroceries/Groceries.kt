package de.shopnchop.provideGroceries

import java.util.Date

data class Groceries(
    val id: String?,
    val name: String,
    var amount: Double,
    var currentExpirationDate: Date?,
    val purchaseDate: Date? = null,
    val newExpirationDate: Date? = null
)