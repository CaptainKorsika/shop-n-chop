package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideRecipes.RecipeEntity
import de.shopnchop.provideRecipes.RecipeIngredient
import de.shopnchop.provideRecipes.RecipeIngredientEntity
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecipeEntityConverterTest {
    private val ingredientEntityConverterMock: RecipeIngredientEntityConverter = mockk()
    private lateinit var recipeEntityConverter: RecipeEntityConverter


    @BeforeEach
    fun setUp() {
        recipeEntityConverter = RecipeEntityConverter(ingredientEntityConverterMock)
    }

    @Test
    fun `should convert recipe entity to domain object`() {
        // given
        val recipeEntity = RecipeEntity(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredientEntity("Tomaten", 5, "Pieces"),
                RecipeIngredientEntity("Brühe", 1, "Liter")
            )
        )

        every { ingredientEntityConverterMock.entityToDomain(any()) } returnsMany listOf(
            RecipeIngredient("Tomaten", 5, "Pieces"),
            RecipeIngredient("Brühe", 1, "Liter")
        )


        // when
        val recipe = recipeEntityConverter.entityToDomain(recipeEntity)

        // then
        val expectedRecipe = Recipe(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredient("Tomaten", 5, "Pieces"),
                RecipeIngredient("Brühe", 1, "Liter")
            )
        )
        recipe.shouldBe(expectedRecipe)
        verify { ingredientEntityConverterMock.entityToDomain(RecipeIngredientEntity("Tomaten", 5, "Pieces")) }
        verify { ingredientEntityConverterMock.entityToDomain(RecipeIngredientEntity("Brühe", 1, "Liter")) }
    }


    @Test
    fun `should convert recipe domain object to entity`() {
        // given
        val recipe = Recipe(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredient("Tomaten", 5, "Pieces"),
                RecipeIngredient("Brühe", 1, "Liter")
            )
        )

        every { ingredientEntityConverterMock.domainToEntity(any()) } returnsMany listOf(
            RecipeIngredientEntity("Tomaten", 5, "Pieces"),
            RecipeIngredientEntity("Brühe", 1, "Liter")
        )


        // when
        val recipeEntity = recipeEntityConverter.domainToEntity(recipe)

        // then
        val expectedRecipeEntity = RecipeEntity(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredientEntity("Tomaten", 5, "Pieces"),
                RecipeIngredientEntity("Brühe", 1, "Liter")
            )
        )
        recipeEntity.shouldBe(expectedRecipeEntity)
        verify { ingredientEntityConverterMock.domainToEntity(RecipeIngredient("Tomaten", 5, "Pieces")) }
        verify { ingredientEntityConverterMock.domainToEntity(RecipeIngredient("Brühe", 1, "Liter")) }
    }



}