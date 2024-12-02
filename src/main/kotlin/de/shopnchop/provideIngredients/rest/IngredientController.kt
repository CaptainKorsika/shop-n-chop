package de.shopnchop.provideIngredients.rest

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideIngredients.IngredientProcess
import de.shopnchop.provideIngredients.converter.IngredientDtoConverter
import de.shopnchop.provideIngredients.dto.IngredientDTO
import org.springframework.http.ResponseEntity
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
    fun provideIngredients(): List<IngredientDTO> {
        return ingredientProcess.fetchIngredients().map { ingredientDtoConverter.domainToDTO(it) }
    }

    @GetMapping("ingredient/{name}")
    fun provideIngredientByName(@PathVariable name: String): ResponseEntity<IngredientDTO> {
        val ingredient = ingredientProcess.fetchIngredientByName(name)
        return if (ingredient != null) {
            val ingredientDto = ingredientDtoConverter.domainToDTO(ingredient)
            ResponseEntity.ok(ingredientDto)
        } else {
            ResponseEntity.badRequest().build()
        }

    }

    @PostMapping("/ingredient/{name}")
    fun createIngredient(@RequestBody dto: IngredientDTO) {
        val ingredient = ingredientDtoConverter.dtoToDomain(dto)
        ingredientProcess.saveIngredient(ingredient)
    }

    @PostMapping("deleteIngredient/{name}")
    fun deleteIngredientByName(@PathVariable name: String): List<IngredientDTO> {
        ingredientProcess.deleteIngredient(name)
        return ingredientProcess
            .fetchIngredients()
            .map { ingredientDtoConverter.domainToDTO(it) }
    }
}