package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideIngredients.dto.RecipeIngredientDTO
import de.shopnchop.provideRecipes.Recipe
import de.shopnchop.provideRecipes.RecipeIngredient
import de.shopnchop.provideRecipes.dto.RecipeDTO
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecipeDtoConverterTest{
    private val ingredientDtoConverter: RecipeIngredientDtoConverter = mockk()
    private lateinit var recipeDtoConverter: RecipeDtoConverter


    @BeforeEach
    fun setUp() {
            recipeDtoConverter = RecipeDtoConverter(ingredientDtoConverter)
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
                RecipeIngredientDTO("Tomaten", 5, "Pieces"),
                RecipeIngredientDTO("Brühe", 1, "Liter")
            )
        )

        every { ingredientDtoConverter.dtoToDomain(any()) } returnsMany listOf(
            RecipeIngredient("Tomaten", 5, "Pieces"),
            RecipeIngredient("Brühe", 1, "Liter")
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
                RecipeIngredient("Tomaten", 5, "Pieces"),
                RecipeIngredient("Brühe", 1, "Liter")
            )
        )
        recipe.shouldBe(expectedRecipe)
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
                RecipeIngredient("Tomaten", 5, "Pieces"),
                RecipeIngredient("Brühe", 1, "Liter")
            )
        )

        every { ingredientDtoConverter.domainToDto(any()) } returnsMany listOf(
            RecipeIngredientDTO("Tomaten", 5, "Pieces"),
            RecipeIngredientDTO("Brühe", 1, "Liter")
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
                RecipeIngredientDTO("Tomaten", 5, "Pieces"),
                RecipeIngredientDTO("Brühe", 1, "Liter")
            )
        )
        recipeDto.shouldBe(expectedRecipe)
    }


}