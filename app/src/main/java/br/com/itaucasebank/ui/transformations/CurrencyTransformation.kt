package br.com.itaucasebank.ui.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import br.com.itaucasebank.core.formatToCurrency

class CurrencyTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.text.formatToCurrency()
        val offsetMapping = handleOffsetMapping(formattedText, text.text)
        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = offsetMapping,
        )
    }

    private fun handleOffsetMapping(formattedText: String, text: String) = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return formattedText.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            return text.length
        }
    }
}
