package br.com.itaucasebank.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Orange

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Orange)
    }
}

@Preview
@Composable
fun LoadingComponentPreview(){
    ItaucasebankTheme {
        LoadingComponent()
    }
}