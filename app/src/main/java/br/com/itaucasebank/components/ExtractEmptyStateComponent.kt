package br.com.itaucasebank.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.R
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.OrangePrimary

@Composable
fun ExtractEmptyStateComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp),
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            tint = OrangePrimary,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = stringResource(id = R.string.extract_empty_state_component_title)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            fontSize = 16.sp,
            text = stringResource(id = R.string.extract_empty_state_component_message),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun ExtractEmptyStateComponentPreview() {
    ItauCaseBankTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            ExtractEmptyStateComponent()
        }
    }
}
