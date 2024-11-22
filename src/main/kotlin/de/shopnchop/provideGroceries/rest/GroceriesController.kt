package de.shopnchop.provideGroceries.rest

import de.shopnchop.provideGroceries.GroceriesProcess
import de.shopnchop.provideGroceries.converter.GroceriesDtoConverter
import de.shopnchop.provideGroceries.dto.GroceriesDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GroceriesController(
    val groceriesDtoConverter: GroceriesDtoConverter,
    val groceriesProcess: GroceriesProcess,
) {

    @GetMapping("/groceries")
    fun provideCalculatedGroceries(): List<GroceriesDTO> {
//        val selectedRecipes = selectedRecipeDtos.map { recipeDtoConverter.dtoToDomain(it) }
//        val neededIngredients = selectedRecipes.map { it.ingredients. }
        return groceriesProcess.fetchGroceries().map { groceriesDtoConverter.domainToDTO(it) }
//        val missingGroceries =
    }


    @PostMapping("/groceries")
    fun confirmGroceries(@RequestBody dto: List<GroceriesDTO>) {
        val groceries = dto.map { groceriesDtoConverter.dtoToDomain(it) }
        groceriesProcess.saveGroceries(groceries)
    }

    @PostMapping("/cook")
    fun useGroceries(@RequestBody dtoList: List<GroceriesDTO>) {
        val usedGroceries = dtoList.map { groceriesDtoConverter.dtoToDomain(it) }
        groceriesProcess.useGroceries(usedGroceries)
    }
}


//}