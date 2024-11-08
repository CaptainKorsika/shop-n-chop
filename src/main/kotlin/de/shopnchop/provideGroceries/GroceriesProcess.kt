package de.shopnchop.provideGroceries

import de.shopnchop.provideGroceries.converter.GroceriesEntityConverter
import de.shopnchop.provideRecipes.Recipe
import org.springframework.stereotype.Component

@Component
class GroceriesProcess(val groceriesRepository: GroceriesRepository, val groceriesEntityConverter: GroceriesEntityConverter) {
    fun fetchGroceries(): List<Groceries> {
        val groceriesList = mutableListOf<Groceries>()
        groceriesRepository.findAll().map { groceriesList.add(groceriesEntityConverter.entityToDomain(it)) }.toList()

        return groceriesList
    }

    fun saveGroceries(groceries: List<Groceries>) {
        groceries.map { groceriesRepository.save(groceriesEntityConverter.domainToEntity(it)) }
    }

    fun deleteGroceries(groceries: List<Groceries>) {
        // TODO:
    }


}