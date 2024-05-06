package br.com.itaucasebank.extension

import br.com.itaucasebank.core.formatToAccountNumber
import br.com.itaucasebank.core.formatToAccountNumberComponent
import br.com.itaucasebank.core.formatToCurrency
import br.com.itaucasebank.core.formatToDateTimePattern
import br.com.itaucasebank.core.formatToDigits
import br.com.itaucasebank.core.formatToMask
import br.com.itaucasebank.ui.transformations.MaskType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class FormatterExtensionsTest {

    @Test
    fun `test account number formatting with length greater than or equal to 7`() {
        val accountNumber = "1234567890"
        val formattedAccountNumber = accountNumber.formatToAccountNumber()
        assertEquals("**34567-890", formattedAccountNumber)
    }

    @Test
    fun `test account number formatting with length less than 7`() {
        val accountNumber = "123456"
        val formattedAccountNumber = accountNumber.formatToAccountNumber()
        assertEquals("123456", formattedAccountNumber)
    }

    @Test
    fun `test account number component formatting with length 6`() {
        val accountNumberComponent = "123456"
        val formattedAccountNumberComponent = accountNumberComponent.formatToAccountNumberComponent()
        assertEquals("12345-6", formattedAccountNumberComponent)
    }

    @Test
    fun `test account number component formatting with length not equal to 6`() {
        val accountNumberComponent = "12345"
        val formattedAccountNumberComponent = accountNumberComponent.formatToAccountNumberComponent()
        assertEquals("12345", formattedAccountNumberComponent)
    }

    @Test
    fun `test date time pattern formatting`() {
        val apiDateFormat = "2024-04-18T04:00:22.469Z"
        val formattedDateTime = apiDateFormat.formatToDateTimePattern()
        assertEquals("18/04/2024, 04:00 AM", formattedDateTime)
    }

    @Test
    fun `test currency formatting with default parameters`() {
        val amount = "10000"
        val formattedAmount = amount.formatToCurrency()
        assertEquals("R$ 100,00", formattedAmount)
    }

    @Test
    fun `test currency formatting without currency symbol`() {
        val amount = "50000"
        val formattedAmount = amount.formatToCurrency(addCurrencySymbol = false)
        assertEquals("500,00", formattedAmount)
    }

    @Test
    fun `test formatToDigits with digits only`() {
        val input = "12345"
        val formatted = input.formatToDigits()
        assertEquals("12345", formatted)
    }

    @Test
    fun `test formatToDigits with alphanumeric characters`() {
        val input = "a1b2c3"
        val formatted = input.formatToDigits()
        assertEquals("123", formatted)
    }

    @Test
    fun `test formatToMask with maskType`() {
        val input = "08990304465"
        val maskType = MaskType.CPF_MASK
        val formatted = input.formatToMask(maskType)
        assertEquals("089.903.044-65", formatted)
    }

    @Test
    fun `test formatToMask with custom mask`() {
        val input = "1234567890"
        val customMask = "###-###-####"
        val formatted = input.formatToMask(customMask)
        assertEquals("123-456-7890", formatted)
    }
}
