package de.shopnchop.provideRecipes.rest

import de.shopnchop.provideRecipes.RecipeProcess
import de.shopnchop.provideRecipes.converter.RecipeDtoConverter
import de.shopnchop.provideRecipes.converter.RecipeIngredientDtoConverter
import de.shopnchop.provideRecipes.dto.RecipeDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class RecipeController(
    private val recipeDtoConverter: RecipeDtoConverter,
    private val recipeProcess: RecipeProcess
) {
    @GetMapping("/recipes")
    fun provideRecipes(): List<RecipeDTO> {
        val recipes = recipeProcess.fetchRecipes().map { recipeDtoConverter.domainToDTO(it) }
        return recipes
    }

    @GetMapping("/recipes/{id}")
    fun provideRecipeByID(@PathVariable id: String): ResponseEntity<RecipeDTO> {

        val recipe = recipeProcess.fetchRecipeById(id)
        return if (recipe != null) {
            val recipeDto =  recipeDtoConverter.domainToDTO(recipe)
            ResponseEntity.ok(recipeDto)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/recipes")
    fun createRecipe(@RequestBody dto: RecipeDTO): ResponseEntity<String> {

        val recipe = recipeDtoConverter.dtoToDomain(dto)
        recipeProcess.saveRecipe(recipe)

        return ResponseEntity.accepted().build()
    }

}