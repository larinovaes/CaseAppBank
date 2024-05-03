package br.com.itaucasebank.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import br.com.itaucasebank.R

@Composable
fun TextComponent(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = getDefaultFontFamily(),
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) {
    Text(
        text = text,
        color = color,
        fontFamily = fontFamily,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = fontSize,
        modifier = modifier,
        maxLines = maxLines,
        minLines = minLines,
    )
}

private fun getDefaultFontFamily(): FontFamily {
    return FontFamily(
        Font(R.font.dmsans_light, FontWeight.Light),
        Font(R.font.dmsans_regular, FontWeight.Normal),
        Font(R.font.dmsans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.dmsans_medium, FontWeight.Medium),
        Font(R.font.dmsans_bold, FontWeight.Bold)
    )
}
