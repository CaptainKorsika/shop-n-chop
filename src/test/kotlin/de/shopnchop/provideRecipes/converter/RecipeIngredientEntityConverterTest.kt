package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideRecipes.RecipeIngredient
import de.shopnchop.provideRecipes.RecipeIngredientEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecipeIngredientEntityConverterTest{
    private lateinit var recipeIngredientEntityConverter: RecipeIngredientEntityConverter

    @BeforeEach
    fun setUp() {
        recipeIngredientEntityConverter = RecipeIngredientEntityConverter()
    }


    @Test
    fun `should convert recipeIngredient to entity`() {
        // given
        val recipeIngredient = RecipeIngredient("Tomato", 5, "pieces")


        // when
        val recipeIngredientEntity = recipeIngredientEntityConverter.domainToEntity(recipeIngredient)


        // then
        val expectedEntity = RecipeIngredientEntity("Tomato", 5, "pieces")
        recipeIngredientEntity.shouldBe(expectedEntity)
    }

    @Test
    fun `should convert recipeIngredientEntity to domain object`() {
        // given
        val testee = RecipeIngredientEntity("Tomato", 5, "pieces")


        // when
        val recipeIngredientEntity = recipeIngredientEntityConverter.entityToDomain(testee)


        // then
        val expectedRecipeIngredient = RecipeIngredient("Tomato", 5, "pieces")
        recipeIngredientEntity.shouldBe(expectedRecipeIngredient)
    }


}