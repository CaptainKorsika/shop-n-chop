package de.shopnchop.provideIngredients.converter

import de.shopnchop.provideGroceries.Groceries
import de.shopnchop.provideGroceries.GroceriesEntity
import de.shopnchop.provideGroceries.converter.GroceriesEntityConverter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GroceriesEntityConverterUnitTest {
    lateinit var groceriesEntityConverter: GroceriesEntityConverter

    @BeforeEach
    fun setUp() {
        groceriesEntityConverter = GroceriesEntityConverter()
    }

    @Test
    fun `should convert groceries domain object to entity`() {
        // given
        val groceries = Groceries(
            "Tomato",
            "10",
            "2024-12-20"
        )

        // when
        val groceriesEntity = groceriesEntityConverter.domainToEntity(groceries)

        // then
        val expectedEntity = GroceriesEntity(
            "Tomato",
            "10",
            "2024-12-20"
        )

        groceriesEntity.shouldBe(expectedEntity)
    }

    @Test
    fun `should convert groceriesEntity to domain object`() {
        // given
        val groceriesEntity = GroceriesEntity(
            "Tomato",
            "10",
            "2024-12-20"
        )

        // when
        val groceries = groceriesEntityConverter.entityToDomain(groceriesEntity)

        // then
        val expectedGroceries = Groceries(
            "Tomato",
            "10",
            "2024-12-20"
        )

        groceries.shouldBe(expectedGroceries)
    }




}