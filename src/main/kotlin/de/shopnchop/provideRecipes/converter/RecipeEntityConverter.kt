package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideRecipes.RecipeEntity
import org.springframework.stereotype.Component

@Component
class RecipeEntityConverter {

    fun domainToEntity(recipe: Recipe): RecipeEntity {


        return RecipeEntity()
    }


    fun entityToDomain(entity: RecipeEntity): Recipe {


        return Recipe()
    }

}