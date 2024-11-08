package de.shopnchop.provideIngredients.rest

import de.shopnchop.provideIngredients.IngredientProcess
import de.shopnchop.provideIngredients.converter.IngredientDtoConverter
import de.shopnchop.provideIngredients.dto.IngredientDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IngredientController(
    private val ingredientDtoConverter: IngredientDtoConverter,
    private val ingredientProcess: IngredientProcess
) {


    @GetMapping("/ingredients")
    fun provideIngredients() {

    }

    @GetMapping("ingredient/{name}")
    fun provideIngredientByName(@PathVariable name: String) {

    }


    @PostMapping("/ingredient/{name}")
    fun createIngredient(@RequestBody dto: IngredientDTO) {

    }


//    fun deleteIngredient() {
//
//
//        future implementation
//
//    }





}