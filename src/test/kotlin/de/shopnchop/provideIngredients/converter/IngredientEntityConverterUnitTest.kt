package de.shopnchop.provideIngredients.converter

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideIngredients.IngredientEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class IngredientEntityConverterUnitTest {
    lateinit var ingredientEntityConverter: IngredientEntityConverter

    @BeforeEach
    fun setUp() {
        ingredientEntityConverter = IngredientEntityConverter()
    }

    @Test
    fun `should convert ingredient domain object to entity`() {
        // given
        val ingredient = Ingredient(
            "Tomato",
            "pieces",
            "vegetables",
            "7"
        )

        // when
        val ingredientEntity = ingredientEntityConverter.domainToEntity(ingredient)

        // then
        val expectedEntity = IngredientEntity(
            "Tomato",
            "pieces",
            "vegetables",
            "7"
        )

        ingredientEntity.shouldBe(expectedEntity)
    }

    @Test
    fun `should convert ingredientEntity to domain object`() {
        // given
        val ingredientEntity = IngredientEntity(
            "Tomato",
            "pieces",
            "vegetables",
            "7"
        )

        // when
        val ingredient = ingredientEntityConverter.entityToDomain(ingredientEntity)

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