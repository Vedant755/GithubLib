package com.hadiyarajesh.mad_s05.testing.unit

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @After
    fun tearDown() {
        println("completed")
    }

    @Test
    fun testAddition() {
        // setup
//        val calculator = Calculator()
        // action
        val result = calculator.add(5, 5)
        // verification - assertion
        Assert.assertEquals(10, result)
    }

    @Test
    fun testSubtraction() {
        val result = calculator.subtract(10, 5)
        Assert.assertEquals(5, result)
    }

    @Test
    fun testMultiplication() {
        val result = calculator.multiply(10, 5)
        Assert.assertEquals(50, result)
    }

    @Test
    fun testDivision() {
        val result = calculator.divide(10, 5)
        Assert.assertEquals(2, result)
    }

    @Test(expected = IllegalArgumentException::class)
//    fun testDivisionWithException() {
    fun `throws exception when divide by 0`() {
        val result = calculator.divide(10, 0)
    }
}
