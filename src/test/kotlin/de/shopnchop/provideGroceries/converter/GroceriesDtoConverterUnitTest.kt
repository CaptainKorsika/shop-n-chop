package de.shopnchop.provideIngredients.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.converter.GroceriesDtoConverter
import de.shopnchop.provideGroceries.dto.GroceriesDTO
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GroceriesDtoConverterUnitTest {
    lateinit var groceriesDtoConverter: GroceriesDtoConverter

    @BeforeEach
    fun setUp() {
        groceriesDtoConverter = GroceriesDtoConverter()
    }

    @Test
    fun `should convert groceries domain object to dto`() {
        // given
        val groceries = Groceries(
            "Tomato",
            "10",
            "2024-12-20"
        )

        // when
        val groceriesDto = groceriesDtoConverter.domainToDTO(groceries)

        // then
        val expectedDto = GroceriesDTO(
            "Tomato",
            "10",
            "2024-12-20"
        )

        groceriesDto.shouldBe(expectedDto)
    }

    @Test
    fun `should convert groceriesDto to domain object`() {
        // given
        val groceriesDto = GroceriesDTO(
            "Tomato",
            "10",
            "2024-12-20"
        )

        // when
        val groceries = groceriesDtoConverter.dtoToDomain(groceriesDto)

        // then
        val expectedGroceries = Groceries(
            "Tomato",
            "10",
            "2024-12-20"
        )

        groceries.shouldBe(expectedGroceries)
    }




}