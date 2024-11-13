package de.shopnchop.provideIngredients.converter

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideIngredients.dto.IngredientDTO
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class IngredientDtoConverterUnitTest {
    lateinit var ingredientDtoConverter: IngredientDtoConverter

    @BeforeEach
    fun setUp() {
        ingredientDtoConverter = IngredientDtoConverter()
    }

    @Test
    fun `should convert domain object to dto`() {
        // given
        val ingredient = Ingredient(
             "Tomato",
            "pieces",
            "vegetables",
            "7"
        )

        // when
        val ingredientDto = ingredientDtoConverter.domainToDTO(ingredient)

        // then
        val expectedDto = IngredientDTO(
            "Tomato",
            "pieces",
            "vegetables",
            "7"
        )

        ingredientDto.shouldBe(expectedDto)
    }

    @Test
    fun `should convert ingredientDto to domain object`() {
        // given
        val ingredientDto = IngredientDTO(
            "Tomato",
            "pieces",
            "vegetables",
            "7"
        )

        // when
        val ingredient = ingredientDtoConverter.dtoToDomain(ingredientDto)

        // then
        val expectedIngredient = Ingredient(
            "Tomato",
            "pieces",
            "vegetables",
            "7"
        )

        ingredient.shouldBe(expectedIngredient)
    }




}