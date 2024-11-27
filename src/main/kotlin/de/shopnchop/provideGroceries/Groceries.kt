package de.shopnchop.provideGroceries

import java.util.Date

data class Groceries(
    val id: String?,
    val name: String,
    var amount: Double,
    val purchaseDate: Date,
    var currentExpirationDate: Date?,
    val newExpirationDate: Date? = null
)