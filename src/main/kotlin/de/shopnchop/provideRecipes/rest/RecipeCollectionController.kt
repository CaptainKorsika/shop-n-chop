package de.shopnchop.provideRecipes.rest

import de.shopnchop.shared.dto.RecipeDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/recipes")
class RecipeCollectionController {


    @GetMapping
    fun provideRecipes(): List<RecipeDTO> {




//        """
//            {
//        "id": "001",
//        "name": "Noodles",
//        "amount": "100",
//        "unit": "grams",
//        "category": "Grain products",
//        "durability": "90"
//      }
//        """.trimIndent()

      val dtoList = mutableListOf<RecipeDTO>()
      return dtoList
    }

    @PostMapping("newRecipe")
    fun receiveRecipes() {
    }
}