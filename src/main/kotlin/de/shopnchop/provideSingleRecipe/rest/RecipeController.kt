package de.shopnchop.provideRecipes.rest

import de.shopnchop.provideRecipes.dto.IngredientDTO
import de.shopnchop.provideRecipes.dto.RecipeDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/recipe")
class RecipeController {


    @GetMapping
    fun provideRecipes(): RecipeDTO {

//        return """
//            {
//        "id": "001",
//        "name": "Noodles",
//        "amount": "100",
//        "unit": "grams",
//        "category": "Grain products",
//        "durability": "90"
//      }
//        """.trimIndent()
//    }

        return RecipeDTO(
            "001",
            "Spaghetti",
            "this is how tou cook awesome spaghetti",
            "20 Minutes",
            listOf(
                IngredientDTO("001", "Noodles", "100", "grams", "Grain products", "90"),
                IngredientDTO("002", "Tomatoes", "5", "pieces", "Vegetables", "7")
            )
        )
    }


}