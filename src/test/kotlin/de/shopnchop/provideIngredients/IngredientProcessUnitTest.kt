package de.shopnchop.provideIngredients

import de.shopnchop.provideIngredients.converter.IngredientEntityConverter
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify

class IngredientProcessUnitTest {
    val ingredientRepositoryMock: IngredientRepository = mockk()
    val ingredientEntityConverterMock: IngredientEntityConverter = mockk()
    lateinit var ingredientProcess: IngredientProcess

    @BeforeEach
    fun setup() {
        ingredientProcess = IngredientProcess(ingredientRepositoryMock, ingredientEntityConverterMock)
    }


    @Test
    fun `should fetch all ingredients`() {
        // given

        val expectedIngredientEntity = IngredientEntity("Tomato", "pieces", "vegetables", "7")
        val expectedIngredient = Ingredient("Tomato", "pieces", "vegetables", "7")

        every { ingredientRepositoryMock.findAll() } returns listOf(expectedIngredientEntity)
        every { ingredientEntityConverterMock.entityToDomain(any()) } returns expectedIngredient

        // when
        val result = ingredientProcess.fetchIngredients()

        // then
        result.shouldBe(listOf(expectedIngredient))
        verify { ingredientRepositoryMock.findAll() }
        verify { ingredientEntityConverterMock.entityToDomain(expectedIngredientEntity) }

    }

    @Test
    fun `should return empty list, when not ingredient found`() {
        // given
        // when
        // then
    }

    @Test
    fun `should throw error when converter gets corrupted data`() {
        // given
        // when
        // then
    }

    @Test
    fun `should fetch correct ingredient when name is entered`() {
        // given
        // when
        // then
    }

    @Test
    fun `should return null when recipe not in db`() {
        // given
        // when
        // then
    }

    @Test
    fun `should save recipe with correct input`() {
        // given
        // when
        // then
    }

    @Test
    fun `should throw error with wrong input`() {
        // given
        // when
        // then
    }




}