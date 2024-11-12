package de.shopnchop.provideIngredients

import de.shopnchop.provideIngredients.converter.IngredientEntityConverter
import org.springframework.stereotype.Component

@Component
class IngredientProcess(
    val ingredientRepository: IngredientRepository,
    val ingredientEntityConverter: IngredientEntityConverter
) {

    fun fetchIngredients(): List<Ingredient> {
        val ingredientList = mutableListOf<Ingredient>()
        ingredientRepository.findAll().map { ingredientList.add(ingredientEntityConverter.entityToDomain(it)) }.toList()

        return ingredientList
    }


    fun fetchIngredientByName(name: String): Ingredient? {
        val ingredientOrNull = ingredientRepository.findByName(name)
        if (ingredientOrNull.isEmpty()) {
            return null
        }
        return ingredientEntityConverter.entityToDomain(ingredientOrNull.get())
    }


    fun saveIngredient(ingredient: Ingredient) {
        val ingredientEntity = ingredientEntityConverter.domainToEntity(ingredient)
        ingredientRepository.save(ingredientEntity)
    }


}