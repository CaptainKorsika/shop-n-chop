package de.shopnchop.provideIngredients

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface IngredientRepository: MongoRepository<IngredientEntity, String> {
    fun findByName(name: String): Optional<IngredientEntity>
}