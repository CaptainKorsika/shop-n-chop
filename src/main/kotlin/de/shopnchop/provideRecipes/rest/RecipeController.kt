package de.shopnchop.provideRecipes.rest

import de.shopnchop.shared.dto.IngredientDTO
import de.shopnchop.shared.dto.RecipeDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/recipe")
class RecipeController {


    @GetMapping
    fun provideRecipes(): RecipeDTO {


        return RecipeDTO(
            "001",
            "Spaghetti",
            "this is how you cook awesome spaghetti",
            "20 Minutes",
            listOf(
                IngredientDTO("Noodles", "100", "grams", "Grain products", "90"),
                IngredientDTO("Tomatoes", "5", "pieces", "Vegetables", "7")
            )
        )
    }


}