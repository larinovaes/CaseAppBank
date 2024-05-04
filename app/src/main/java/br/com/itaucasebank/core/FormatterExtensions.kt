package br.com.itaucasebank.core

import br.com.itaucasebank.ui.transformations.MaskType
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

fun Date.formatToSimpleDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return formatter.format(this)
}

fun Date.formatToHour(): String {
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

    return formatter.format(this)
}

fun String.formatToCurrency(addCurrencySymbol: Boolean = true): String {
    val currencyMin = 3
    val currencyMax = 6
    val userInput = this.formatToDigits()
    val valueBuilder = StringBuilder(userInput)

    while (valueBuilder.length > currencyMin && valueBuilder[0] == '0') {
        valueBuilder.deleteCharAt(0)
    }
    while (valueBuilder.length < currencyMin) {
        valueBuilder.insert(0, '0')
    }

    valueBuilder.insert(valueBuilder.length - 2, ',')

    if (valueBuilder.length > currencyMax) {
        valueBuilder.insert(valueBuilder.length - currencyMax, ".")
    }

    var finalValue = valueBuilder.toString()

    if (addCurrencySymbol) {
        finalValue = "R$ $finalValue"
    }
    return finalValue
}

fun String.formatToDigits(): String {
    val nonDigitRegex = "\\D"
    return this.replace(nonDigitRegex.toRegex(), "")
}

fun String.formatToMask(maskType: MaskType): String {
    return formatToMask(maskType.mask)
}

fun String.formatToMask(mask: String): String {
    val unmaskedString = this.formatToDigits()
    val maskedString = StringBuilder("")
    var i = 0

    for (m in mask.toCharArray()) {
        if (m != '#' && i < unmaskedString.length) {
            maskedString.append(m)
            continue
        }
        try {
            maskedString.append(unmaskedString[i])
        } catch (e: Exception) {
            break
        }
        i++
    }
    return maskedString.toString()
}
