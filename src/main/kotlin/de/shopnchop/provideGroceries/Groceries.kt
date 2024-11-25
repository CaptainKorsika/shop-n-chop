package de.shopnchop.provideGroceries

import java.util.Date

data class Groceries(
    val name: String,
    val amount: Double,
    val expirationDate: Date?
)