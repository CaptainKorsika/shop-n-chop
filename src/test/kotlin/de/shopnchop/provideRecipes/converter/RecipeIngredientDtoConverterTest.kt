package de.shopnchop.provideRecipes.converter

import de.shopnchop.provideIngredients.dto.RecipeIngredientDTO
import de.shopnchop.provideRecipes.RecipeIngredient
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecipeIngredientDtoConverterTest {
    lateinit var recipeIngredientDtoConverter: RecipeIngredientDtoConverter

    @BeforeEach
    fun setUp() {
        recipeIngredientDtoConverter = RecipeIngredientDtoConverter()
    }

    @Test
    fun `should convert recipeIngredientDto to domain object`() {
        // given
        val dto = RecipeIngredientDTO(
            "Apple",
            1,
            "kg"
        )

        // when
        val recipeIngredient = recipeIngredientDtoConverter.dtoToDomain(dto)


        // then
        val expectedRecipeIngredient = RecipeIngredient(
            "Apple",
            1,
            "kg"
        )

        recipeIngredient.shouldBe(expectedRecipeIngredient)

    }

    @Test
    fun `should convert recipeIngredient to dto`() {
        // given
        val recipeIngredient = RecipeIngredient(
            "Milk",
            200,
            "ml"
        )

        // when
        val recipeIngredientDto = recipeIngredientDtoConverter.domainToDto(recipeIngredient)


        // then
        val expectedRecipeIngredientDto = RecipeIngredientDTO(
            "Milk",
            200,
            "ml"
        )

        recipeIngredientDto.shouldBe(expectedRecipeIngredientDto)

    }





}