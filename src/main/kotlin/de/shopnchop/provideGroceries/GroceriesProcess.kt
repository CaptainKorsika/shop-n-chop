package de.shopnchop.provideGroceries

import de.shopnchop.provideGroceries.converter.GroceriesEntityConverter
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import java.text.SimpleDateFormat
import java.util.*

@Component
class GroceriesProcess(val groceriesRepository: GroceriesRepository, val groceriesEntityConverter: GroceriesEntityConverter) {
    fun fetchGroceries(): List<Groceries> {
        return groceriesRepository
            .findAll()
            .map { groceriesEntityConverter.entityToDomain(it) }
            .toList()

    }

    fun fetchIdByNameAndDate(name: String, date: String): String? {
        val foundEntities = groceriesRepository
            .findByName(name)
            .filter { it.expirationDate == date }

        if (foundEntities.isEmpty()) {
            return null
        }

        return foundEntities.first().id

    }

    fun saveGroceries(groceries: List<Groceries>) {
        val groceriesList = groceries.map {groceriesEntityConverter.domainToEntity(it)}
        groceriesList.forEach {

            val matchingEntryId = this.fetchIdByNameAndDate(it.name, it.expirationDate)
            if (matchingEntryId == null) {
                groceriesRepository.save(it)
            } else {

                groceriesRepository.save(
                    GroceriesEntity(
                        matchingEntryId,
                        it.name,
                        it.amount,
                        it.expirationDate
                    )
                )
            }
        }
    }

    fun useGroceries(@RequestBody usedGroceries: List<Groceries>) {
        val itemsToDelete: MutableList<GroceriesEntity> = mutableListOf()
        val itemsToUpdate: MutableList<GroceriesEntity> = mutableListOf()

        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate = formatter.format(Date())

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = dateFormat.parse(formattedDate)



        usedGroceries.forEach { usedItem ->
            var neededAmount = usedItem.amount
            val matchingItems = this.fetchGroceries()
                .filter { it.name == usedItem.name }
                .filter { it.expirationDate >= date }
                .sortedBy { it.expirationDate }

            if (matchingItems.isEmpty()) {
                return // not enough ingredients
            }

            val availableAmount = matchingItems.sumOf { it.amount }
            if (neededAmount > availableAmount) {
                return // not enough ingredients
            }

            var currentIndex = 0
            while (neededAmount > 0) {
                val foundItem = matchingItems[currentIndex]
                val itemId = this.fetchIdByNameAndDate(
                    foundItem.name,
                    groceriesEntityConverter.domainToEntity(foundItem).expirationDate)

                lateinit var updatedGroceriesEntity: GroceriesEntity

                if (foundItem.amount <= neededAmount) {
                    neededAmount -= foundItem.amount

                    updatedGroceriesEntity = groceriesEntityConverter.domainToEntity(foundItem, itemId)
                    itemsToDelete.add(updatedGroceriesEntity)
                } else {
                    val updatedGrocery = Groceries(
                        foundItem.name,
                        foundItem.amount - neededAmount,
                        foundItem.expirationDate
                    )
                    updatedGroceriesEntity = groceriesEntityConverter.domainToEntity(updatedGrocery, itemId)
                    itemsToUpdate.add(updatedGroceriesEntity)
                    neededAmount = 0.0
                }
                currentIndex++
            }
        }
        itemsToDelete.forEach { groceriesRepository.delete(it) }
        itemsToUpdate.forEach { groceriesRepository.save(it) }
    }



//    fun calculateNeededIngredients(recipes: List<Recipe>): List<Pair<Ingredient, Int>> {
//
//    }

}