package de.shopnchop.provideGroceries

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "groceries")
data class GroceriesEntity(
    @Id
    val id: String? = null,
    val name: String,
    val amount: String,
    val expirationDate: String
)