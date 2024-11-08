package de.shopnchop.provideGroceries.rest

import de.shopnchop.provideGroceries.GroceriesProcess
import de.shopnchop.provideGroceries.converter.GroceriesDtoConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GroceriesController(
    val groceriesDtoConverter: GroceriesDtoConverter,
    val groceriesProcess: GroceriesProcess
) {

    @GetMapping("/groceries")
    fun provideCalculatedGroceries() {

    }


    @PostMapping("/groceries")
    fun confirmGroceries() {

    }

    @PostMapping("/cook")
    fun useGroceries() {

    }


}