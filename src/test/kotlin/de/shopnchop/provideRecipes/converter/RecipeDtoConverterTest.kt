package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideRecipes.dto.RecipeIngredientDTO
import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideRecipes.RecipeIngredient
import de.shopnchop.provideRecipes.dto.RecipeDTO
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecipeDtoConverterTest{
    private val ingredientDtoConverterMock: RecipeIngredientDtoConverter = mockk()
    private lateinit var recipeDtoConverter: RecipeDtoConverter


    @BeforeEach
    fun setUp() {
            recipeDtoConverter = RecipeDtoConverter(ingredientDtoConverterMock)
    }


    @Test
    fun `should convert recipeDto to domain object`() {
        // given
        val recipeDto = RecipeDTO(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredientDTO("Tomaten", "5", "Pieces"),
                RecipeIngredientDTO("Brühe", "1", "Liter")
            )
        )

        every { ingredientDtoConverterMock.dtoToDomain(any()) } returnsMany listOf(
            RecipeIngredient("Tomaten", 5.0, "Pieces"),
            RecipeIngredient("Brühe", 1.0, "Liter")
        )


        // when
        val recipe = recipeDtoConverter.dtoToDomain(recipeDto)

        // then
        val expectedRecipe = Recipe(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredient("Tomaten", 5.0, "Pieces"),
                RecipeIngredient("Brühe", 1.0, "Liter")
            )
        )
        recipe.shouldBe(expectedRecipe)
        verify { ingredientDtoConverterMock.dtoToDomain(RecipeIngredientDTO("Tomaten", "5", "Pieces")) }
        verify { ingredientDtoConverterMock.dtoToDomain(RecipeIngredientDTO("Brühe", "1", "Liter")) }
    }


    @Test
    fun `should convert recipe domain object to dto`() {
        // given
        val recipe = Recipe(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredient("Tomaten", 5.0, "Pieces"),
                RecipeIngredient("Brühe", 1.0, "Liter")
            )
        )

        every { ingredientDtoConverterMock.domainToDto(any()) } returnsMany listOf(
            RecipeIngredientDTO("Tomaten", "5", "Pieces"),
            RecipeIngredientDTO("Brühe", "1", "Liter")
        )

        // when
        val recipeDto = recipeDtoConverter.domainToDTO(recipe)

        // then
        val expectedRecipe = RecipeDTO(
            "0001",
            "Tomatensuppe",
            "cooking is fun",
            "30 Minutes",
            listOf(
                RecipeIngredientDTO("Tomaten", "5", "Pieces"),
                RecipeIngredientDTO("Brühe", "1", "Liter")
            )
        )
        recipeDto.shouldBe(expectedRecipe)
        verify { ingredientDtoConverterMock.domainToDto(RecipeIngredient("Tomaten", 5.0, "Pieces")) }
        verify { ingredientDtoConverterMock.domainToDto(RecipeIngredient("Brühe", 1.0, "Liter")) }
    }
}