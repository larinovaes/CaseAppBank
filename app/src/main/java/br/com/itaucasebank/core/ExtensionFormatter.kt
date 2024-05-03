package br.com.itaucasebank.core

import android.icu.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

private const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun String.formatToAccountNumber(): String {
    return if (length >= 7) {
        val firstPart = "**" // Substitui os dois primeiros dígitos por asteriscos
        val secondPart = substring(2, 7)
        val thirdPart = substring(7)
        "$firstPart$secondPart-$thirdPart"
    } else {
        this // Retorna o número de conta sem formatação se for menor que 5 dígitos
    }
}

fun String.formatToAccountNumberComponent(): String {
    return if (this.length == 6) {
        "${this.substring(0, 5)}-${this.substring(5)}"
    } else {
        this // Retorna a string original se não tiver o comprimento esperado
    }
}

fun String.formatToCurrency(): String {
    val sanitizedValue = this.replace(Regex("[^\\d.]"), "")
    val number = try {
        NumberFormat.getNumberInstance(Locale.getDefault()).parse(sanitizedValue)?.toDouble() ?: 0.0
    } catch (e: Exception) {
        0.0
    }
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(number)
}

fun String.formatToDateTimePattern(): String {
    return try {
        val fromApiDatePattern = DateTimeFormatter.ofPattern(API_DATE_FORMAT)
        val toUiDatePattern = DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm a")
        val dateTime = LocalDateTime.parse(this, fromApiDatePattern)
        dateTime.format(toUiDatePattern)
    } catch (e: Exception) {
        ""
    }
}

fun String.isValidCPF(): Boolean {
    val numbers = this.filter { it.isDigit() }.map { it.toString().toInt() }
    if (numbers.size != 11 || numbers.toSet().size == 1) return false

    val dv1 = calculateDigit(numbers.take(9))
    val dv2 = calculateDigit(numbers.take(9) + listOf(dv1))

    return dv1 == numbers[9] && dv2 == numbers[10]
}

fun String.formatCPF(): String {
    val numbers = this.filter { it.isDigit() }
    if (numbers.length != 11) return this

    return "${numbers.substring(0, 3)}.${numbers.substring(3, 6)}.${numbers.substring(6, 9)}-${numbers.substring(9)}"
}

fun Date.formatToSimpleDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

   return formatter.format(this)
}

fun  Date.formatToHour(): String {
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

    return formatter.format(this)
}

private fun calculateDigit(numbers: List<Int>): Int {
    var sum = 0
    var multiplier = numbers.size + 1
    for (number in numbers) {
        sum += number * multiplier
        multiplier--
    }
    val remainder = sum % 11
    return if (remainder < 2) 0 else 11 - remainder
}

