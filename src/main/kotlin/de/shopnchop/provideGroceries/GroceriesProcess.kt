package de.shopnchop.provideGroceries

import de.shopnchop.provideGroceries.converter.GroceriesEntityConverter
import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideRecipes.RecipeIngredient
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

    fun saveGroceries(groceries: List<Groceries>) {
        val groceriesList = groceries.map {groceriesEntityConverter.domainToEntity(it)}
        groceriesList.forEach {

            // TODO: Get expiration duration from DB

            val matchingEntry = this.fetchByNameAndDate(it.name, it.expirationDate)
            if (matchingEntry == null) {
                groceriesRepository.save(it)
            } else {

                val newAmount = calcNewGroceriesAmount(it.amount, matchingEntry.amount)


                groceriesRepository.save(
                    GroceriesEntity(
                        matchingEntry.id,
                        it.name,
                        newAmount,
                        it.expirationDate
                    )
                )
            }
        }
    }

    fun useGroceries(@RequestBody usedGroceries: List<Groceries>) {
        val dateToday = this.formatDate(Date())

        usedGroceries.forEach { usedItem ->
            var neededAmount = usedItem.amount
            val matchingItems = this.fetchGroceries()
                .filter { it.name == usedItem.name }
                .filter { it.currentExpirationDate!! >= dateToday }
                .sortedBy { it.currentExpirationDate }

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
                val itemId = this.fetchByNameAndDate(
                    foundItem.name,
                    groceriesEntityConverter.domainToEntity(foundItem).expirationDate)

                lateinit var updatedGroceriesEntity: GroceriesEntity

                if (foundItem.amount <= neededAmount) {
                    neededAmount -= foundItem.amount

                    updatedGroceriesEntity = groceriesEntityConverter.domainToEntity(foundItem)
                    groceriesRepository.delete(updatedGroceriesEntity)
                } else {
                    val updatedGrocery = Groceries(
                        foundItem.id,
                        foundItem.name,
                        foundItem.amount - neededAmount,
                        foundItem.currentExpirationDate
                    )
                    updatedGroceriesEntity = groceriesEntityConverter.domainToEntity(updatedGrocery)
                    groceriesRepository.save(updatedGroceriesEntity)
                    neededAmount = 0.0
                }
                currentIndex++
            }
        }
    }

    fun calculateAllIngredients(recipes: List<Recipe>): List<Groceries> {
        val ingredients = mutableListOf<RecipeIngredient>()


        recipes.forEach { recipe ->
            recipe.ingredients.forEach {
                if (ingredients.map { ingredient -> ingredient.name }.contains( it.name)) {
                    val amount = it.amount
                    ingredients.filter { ingredient -> ingredient.name == it.name }[0].amount += amount
                } else {
                    ingredients.add(it)
                }
            }
        }
        val neededGroceries = this.convertToGroceries(ingredients)
        val availableGroceries = this.fetchGroceries()
        val missingGroceries = mutableListOf<Groceries>()

        val dateToday = this.formatDate(Date())

        neededGroceries.forEach { groceries ->
            val matchingAmount = availableGroceries
                .filter { it.name == groceries.name }
                .filter { it.currentExpirationDate!! >= dateToday }
            if (matchingAmount.isNotEmpty()) {
                val availableAmount = matchingAmount
                    .sumOf { it.amount }
                println(availableAmount)
                missingGroceries.add(Groceries(
                    null,
                    groceries.name,
                    groceries.amount - availableAmount,
                    null
                ))
            }
            else {
                missingGroceries.add(groceries)
            }
        }
        return missingGroceries.filter { it.amount > 0 }
    }

    fun fetchExpiredGroceries(): List<Groceries> {
        val dateToday = this.formatDate(Date())
        return this.fetchGroceries()
            .filter { it.currentExpirationDate!! < dateToday }
    }

    fun fetchFreshGroceries(): List<Groceries> {
        val dateToday = this.formatDate(Date())
        return this.fetchGroceries()
            .filter { it.currentExpirationDate!! >= dateToday }
    }

    fun deleteGroceries(groceryList: List<Groceries>) {
        groceryList.forEach {
            val groceryEntity = groceriesEntityConverter.domainToEntity(it)
            groceriesRepository.deleteById(groceryEntity.id!!)
        }
    }

    fun changeGroceryItem(groceryItem : Groceries) {
        val groceryEntity = groceriesEntityConverter.domainToEntity(groceryItem)
        val updatedGroceryEntity = GroceriesEntity(
            groceryEntity.id,
            groceryEntity.name,
            groceryEntity.amount,
            formatDateToString(groceryItem.newExpirationDate!!)
        )
        groceriesRepository.save(updatedGroceryEntity)
    }




    private fun fetchByNameAndDate(name: String, date: String): GroceriesEntity? {
        val foundEntities = groceriesRepository
            .findByName(name)
            .filter { it.expirationDate == date }

        if (foundEntities.isEmpty()) {
            return null
        }
        return foundEntities.first()
    }

    private fun formatDate(date: Date): Date {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val formattedDate = this.formatDateToString(date)
        return dateFormat.parse(formattedDate)
    }

    private fun formatDateToString(date: Date): String {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return formatter.format(date)
    }

    private fun calcNewGroceriesAmount(firstAmount: String, secondAmount: String): String {
        return (firstAmount.toDouble() + secondAmount.toDouble()).toString()
    }

    private fun convertToGroceries(ingredients: List<RecipeIngredient>): List<Groceries> {
        return ingredients.map { Groceries(
            null,
            it.name,
            it.amount,
            null
        ) }
    }
}