package br.com.itaucasebank.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val inriaSerif = FontFamily(

)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = Black,
        fontFamily = inriaSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
)