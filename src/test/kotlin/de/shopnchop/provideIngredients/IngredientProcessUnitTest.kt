package de.shopnchop.provideIngredients

import de.shopnchop.provideIngredients.converter.IngredientEntityConverter
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import io.mockk.verifyCount
import org.junit.jupiter.api.assertThrows
import java.util.*

class IngredientProcessUnitTest {
    private val ingredientRepositoryMock: IngredientRepository = mockk()
    private val ingredientEntityConverterMock: IngredientEntityConverter = mockk()
    private lateinit var ingredientProcess: IngredientProcess

    @BeforeEach
    fun setup() {
        ingredientProcess = IngredientProcess(ingredientRepositoryMock, ingredientEntityConverterMock)
    }


    @Test
    fun `should fetch all ingredients`() {
        // given

        val expectedIngredientEntity = IngredientEntity("Tomato", "pieces", "vegetables", "7")
        val secondExpectedIngredientEntity = IngredientEntity("Banana", "pieces", "vegetables", "5")

        val expectedIngredient = Ingredient("Tomato", "pieces", "vegetables", "7")
        val secondExpectedIngredient = Ingredient("Banana", "pieces", "vegetables", "5")

        every { ingredientRepositoryMock.findAll() } returns listOf(expectedIngredientEntity, secondExpectedIngredientEntity)
        every { ingredientEntityConverterMock.entityToDomain(any()) } returnsMany  listOf(
            expectedIngredient,
            secondExpectedIngredient
        )

        // when
        val result = ingredientProcess.fetchIngredients()

        // then
        result.shouldBe(listOf(expectedIngredient, secondExpectedIngredient))
        verify { ingredientRepositoryMock.findAll() }
        verify { ingredientEntityConverterMock.entityToDomain(expectedIngredientEntity) }
        verify { ingredientEntityConverterMock.entityToDomain(secondExpectedIngredientEntity) }

    }

    @Test
    fun `should return empty list, when not ingredient found`() {
        // given

        every { ingredientRepositoryMock.findAll() } returns listOf()
        every { ingredientEntityConverterMock.entityToDomain(any()) }

        // when
        val result = ingredientProcess.fetchIngredients()

        // then
        result.shouldBe(listOf())
        verify { ingredientRepositoryMock.findAll() }
        verifyCount { 0 * {ingredientEntityConverterMock.entityToDomain(any()) }}
    }

    @Test
    fun `shouldn't call converter when repository throws error`() {
        // given
        every { ingredientRepositoryMock.findAll() } throws RuntimeException("This is an exception")

        // when
        val error = assertThrows<RuntimeException> { ingredientProcess.fetchIngredients() }

        // then
        error.message shouldBe "This is an exception"
        verify { ingredientRepositoryMock.findAll() }
        verifyCount { 0 * {ingredientEntityConverterMock.entityToDomain(any()) }}
    }

    @Test
    fun `should call converterMock only once when converter throws error`() {
        // given
        val expectedIngredientEntity = IngredientEntity("Tomato", "pieces", "vegetables", "7")
        val secondExpectedIngredientEntity = IngredientEntity("Banana", "pieces", "vegetables", "9")

        every { ingredientRepositoryMock.findAll() } returns listOf(expectedIngredientEntity, secondExpectedIngredientEntity)
        every { ingredientEntityConverterMock.entityToDomain(any()) } throws RuntimeException("This is an exception")

        // when
        val error = assertThrows<RuntimeException> { ingredientProcess.fetchIngredients() }

        // then
        error.message shouldBe "This is an exception"
        verify { ingredientRepositoryMock.findAll() }
        verifyCount { 1 * {ingredientEntityConverterMock.entityToDomain(any()) }}
    }

    @Test
    fun `should fetch correct ingredient when name is entered`() {
        // given
        val name = "Tomato"

        val expectedIngredientEntity = IngredientEntity("Tomato", "pieces", "vegetables", "7")
        val expectedIngredient = Ingredient("Tomato", "pieces", "vegetables", "7")

        every { ingredientRepositoryMock.findByName(name) } returns Optional.of(expectedIngredientEntity)
        every { ingredientEntityConverterMock.entityToDomain(any()) } returns  expectedIngredient

        // when
        val result = ingredientProcess.fetchIngredientByName(name)

        // then
        result.shouldBe(expectedIngredient)
        verify { ingredientRepositoryMock.findByName(name) }
        verify { ingredientEntityConverterMock.entityToDomain(expectedIngredientEntity) }
    }

    @Test
    fun `should return null when recipe not in db`() {
        // given
        val name = "Banana"

        val expectedIngredient = null

        every { ingredientRepositoryMock.findByName(name) } returns Optional.empty()

        // when
        val result = ingredientProcess.fetchIngredientByName(name)

        // then
        result.shouldBe(expectedIngredient)
        verify { ingredientRepositoryMock.findByName(name) }
        verifyCount { 0 * {ingredientEntityConverterMock.entityToDomain(any()) }}
    }

    @Test
    fun `should save recipe with correct input`() {
        // given
        val expectedIngredient = Ingredient("Tomato", "pieces", "vegetables", "7")
        val expectedIngredientEntity = IngredientEntity("Tomato", "pieces", "vegetables", "7")

        every { ingredientEntityConverterMock.domainToEntity(any()) } returns expectedIngredientEntity
        every { ingredientRepositoryMock.save(expectedIngredientEntity) } returns expectedIngredientEntity

        // when
        ingredientProcess.saveIngredient(expectedIngredient)

        // then
        verify { ingredientRepositoryMock.save(expectedIngredientEntity) }
        verify {ingredientEntityConverterMock.domainToEntity(expectedIngredient) }
    }

    @Test
    fun `should throw error with wrong input`() {
        // given
        val expectedIngredient = Ingredient("Tomato", "pieces", "vegetables", "7")
        val expectedIngredientEntity = IngredientEntity("Tomato", "pieces", "vegetables", "7")

        every { ingredientEntityConverterMock.domainToEntity(any()) } returns expectedIngredientEntity
        every { ingredientRepositoryMock.save(expectedIngredientEntity) } returns expectedIngredientEntity

        // when
        ingredientProcess.saveIngredient(expectedIngredient)

        // then
        verify { ingredientRepositoryMock.save(expectedIngredientEntity) }
        verify {ingredientEntityConverterMock.domainToEntity(expectedIngredient) }
    }
}