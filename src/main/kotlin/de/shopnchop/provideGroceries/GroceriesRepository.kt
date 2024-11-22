package de.shopnchop.provideGroceries

import org.springframework.data.mongodb.repository.MongoRepository

interface GroceriesRepository: MongoRepository<GroceriesEntity, String> {
    fun findByName(name: String): MutableList<GroceriesEntity>
}