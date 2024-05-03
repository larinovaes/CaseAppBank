package br.com.itaucasebank.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.itaucasebank.R
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.Orange

@Composable
fun ErrorMessageComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp),
            imageVector = Icons.Filled.Warning,
            contentDescription = null,
            tint = Orange
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = stringResource(id = R.string.error_component_message))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onClick = { onClick() },
            border = BorderStroke(1.dp, Orange),
            shape = RoundedCornerShape(32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.error_component_button_text),
                color = Orange
            )
        }
    }
}

@Preview
@Composable
fun ErrorMessageComponentPreview() {
    ItauCaseBankTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        ) {
            ErrorMessageComponent(
                onClick = {}
            )
        }

    }
}