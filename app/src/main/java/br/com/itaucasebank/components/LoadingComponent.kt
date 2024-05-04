package br.com.itaucasebank.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.OrangePrimary

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier,
    isOverlayEnabled: Boolean = false
) {
    val alpha = if (isOverlayEnabled) 0.60f else 0f
    val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable(
                    enabled = isOverlayEnabled,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {}
                )
                .background(Color.Black.copy(alpha = alpha)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = OrangePrimary)
        }

}

@Preview
@Composable
fun LoadingComponentPreview(){
    ItauCaseBankTheme {
        LoadingComponent()
    }
}