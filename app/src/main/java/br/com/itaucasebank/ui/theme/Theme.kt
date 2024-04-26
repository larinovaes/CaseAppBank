package br.com.itaucasebank.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

@Composable
fun ItaucasebankTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}