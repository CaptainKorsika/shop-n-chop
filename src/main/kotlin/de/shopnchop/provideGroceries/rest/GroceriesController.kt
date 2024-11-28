package de.shopnchop.provideGroceries.rest

import de.shopnchop.provideGroceries.GroceriesProcess
import de.shopnchop.provideGroceries.converter.GroceriesChangeDtoConverter
import de.shopnchop.provideGroceries.converter.GroceriesDtoConverter
import de.shopnchop.provideGroceries.converter.GroceriesPurchaseDtoConverter
import de.shopnchop.provideGroceries.dto.GroceriesChangeDTO
import de.shopnchop.provideGroceries.dto.GroceriesDTO
import de.shopnchop.provideGroceries.dto.GroceriesPurchaseDTO
import de.shopnchop.provideRecipes.converter.RecipeDtoConverter
import de.shopnchop.provideRecipes.dto.RecipeDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GroceriesController(
    val groceriesDtoConverter: GroceriesDtoConverter,
    val groceriesChangeDtoConverter: GroceriesChangeDtoConverter,
    val groceriesPurchaseDtoConverter: GroceriesPurchaseDtoConverter,
    val recipeDtoConverter: RecipeDtoConverter,
    val groceriesProcess: GroceriesProcess,
) {


    // has id

    @GetMapping("/groceries")
    fun provideAllGroceries(): List<GroceriesDTO> {
        return groceriesProcess.fetchGroceries().map { groceriesDtoConverter.domainToDTO(it) }
    }

    @GetMapping("/fresh")
    fun provideFreshGroceries(): List<GroceriesDTO> {
        return groceriesProcess.fetchFreshGroceries().map { groceriesDtoConverter.domainToDTO(it) }
    }

    @GetMapping("/expired")
    fun provideExpiredGroceries(): List<GroceriesDTO> {
        return groceriesProcess.fetchExpiredGroceries().map { groceriesDtoConverter.domainToDTO(it) }
    }

    @PostMapping("/deleteGroceries")
    fun deleteGroceries(@RequestBody dtoList: List<GroceriesDTO>) {
        groceriesProcess.deleteGroceries(dtoList.map { groceriesDtoConverter.dtoToDomain(it) })
    }

    @PostMapping("/changeGrocery")
    fun changeGroceriesExpiration(@RequestBody dto: GroceriesChangeDTO) {
        groceriesProcess.changeGroceryItem(groceriesChangeDtoConverter.dtoToDomain(dto))
    }

    // no id

    @PostMapping("/calculatedGroceries")
    fun provideCalculatedGroceries(@RequestBody selectedRecipeDtos: List<RecipeDTO>): List<GroceriesDTO> {
        val selectedRecipes = selectedRecipeDtos.map { recipeDtoConverter.dtoToDomain(it) }
        val neededGroceries = groceriesProcess.calculateAllIngredients(selectedRecipes).map { groceriesDtoConverter.domainToDTO(it) }
        return neededGroceries
    }

    @PostMapping("/groceries")
    fun confirmGroceries(@RequestBody dto: List<GroceriesPurchaseDTO>) {
        val groceries = dto.map { groceriesPurchaseDtoConverter.dtoToDomain(it) }
        groceriesProcess.saveGroceries(groceries)
    }

    @PostMapping("/cook")
    fun useGroceries(@RequestBody dtoList: List<GroceriesDTO>) {
        val usedGroceries = dtoList.map { groceriesDtoConverter.dtoToDomain(it) }
        groceriesProcess.useGroceries(usedGroceries)
    }
}




