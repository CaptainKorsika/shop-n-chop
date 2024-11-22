package de.shopnchop.provideIngredients.rest

import de.shopnchop.provideIngredients.Ingredient
import de.shopnchop.provideIngredients.IngredientProcess
import de.shopnchop.provideIngredients.converter.IngredientDtoConverter
import de.shopnchop.provideIngredients.dto.IngredientDTO
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyCount
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class IngredientControllerTest {
    private var ingredientDtoConverterMock: IngredientDtoConverter = mockk()
    private var ingredientProcessMock: IngredientProcess = mockk()
    private lateinit var ingredientController: IngredientController

    @BeforeEach
    internal fun setUp() {
        ingredientController = IngredientController(ingredientDtoConverterMock, ingredientProcessMock)
    }



    @Test
    fun `should fetch all ingredients`() {
        // given
        val givenIngredientObject = Ingredient(
            "Tomato",
            "pieces",
            "Vegetables",
            "7"
        )

        val secondGivenIngredientObject = Ingredient(
            "Banana",
            "pieces",
            "Vegetables",
            "5"
        )

        val expectedDto = IngredientDTO(
            "Tomato",
            "pieces",
            "Vegetables",
            "7"
        )

        val secondExpectedDto = IngredientDTO(
            "Banana",
            "pieces",
            "Vegetables",
            "5"
        )

        every { ingredientProcessMock.fetchIngredients() } returns listOf(givenIngredientObject, secondGivenIngredientObject)
        every { ingredientDtoConverterMock.domainToDTO(any()) } returnsMany listOf(
            expectedDto,
            secondExpectedDto
        )

        // when
        val result = ingredientController.provideIngredients()

        // then
        result shouldBe listOf(expectedDto, secondExpectedDto)
        verify { ingredientProcessMock.fetchIngredients() }
        verifyCount { 2 * {ingredientDtoConverterMock.domainToDTO(any())} }

    }

    @Test
    fun `should fetch ingredient by name`() {
        // given
        val ingredientName = "Tomato"

        val expectedDto = IngredientDTO(
            "Tomato",
            "pieces",
            "Vegetables",
            "7"
        )

        every { ingredientProcessMock.fetchIngredientByName(ingredientName) } returns Ingredient("Tomato", "pieces", "Vegetables", "7")
        every { ingredientDtoConverterMock.domainToDTO(any()) } returns expectedDto

        // when
        val result = ingredientController.provideIngredientByName(ingredientName)

        // then
        result shouldBe ResponseEntity(expectedDto, HttpStatus.OK)
        verify { ingredientProcessMock.fetchIngredientByName(ingredientName) }
        verifyCount { 1 * {ingredientDtoConverterMock.domainToDTO(any())} }
    }

    @Test
    fun `should return bad request, when no valid name is found`() {
        // given
        val ingredientName = "Banana"

        every { ingredientProcessMock.fetchIngredientByName(ingredientName) } returns null
        every { ingredientDtoConverterMock.domainToDTO(any()) }

        // when
        val result = ingredientController.provideIngredientByName(ingredientName)

        // then
        result shouldBe ResponseEntity(HttpStatus.BAD_REQUEST)
        verify { ingredientProcessMock.fetchIngredientByName(ingredientName) }
        verifyCount { 0 * { ingredientDtoConverterMock.domainToDTO(any())} }


    }

    @Test
    fun `should create new ingredient`() {
        // given
        val givenIngredientDTO = IngredientDTO(
            "Tomato",
            "pieces",
            "Vegetables",
            "7"
        )

        val convertedIngredient = Ingredient(
            "Tomato",
            "pieces",
            "Vegetables",
            "7"
        )

        every { ingredientDtoConverterMock.dtoToDomain(any()) } returns convertedIngredient
        every { ingredientProcessMock.saveIngredient(convertedIngredient) } returns Unit

        // when
        ingredientController.createIngredient(givenIngredientDTO)

        // then
        verifyCount { 1 * { ingredientDtoConverterMock.dtoToDomain(any())} }
        verify { ingredientProcessMock.saveIngredient(any()) }

    }

}