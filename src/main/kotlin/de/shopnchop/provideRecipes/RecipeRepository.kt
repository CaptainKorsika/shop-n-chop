package de.shopnchop.provideRecipes

import org.springframework.data.mongodb.repository.MongoRepository

interface RecipeRepository: MongoRepository<RecipeEntity, String>