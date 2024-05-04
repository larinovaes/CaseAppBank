package br.com.itaucasebank.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.ui.theme.Cinza
import br.com.itaucasebank.ui.theme.Cinza2
import br.com.itaucasebank.ui.theme.Cinza3
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.Orange

@Composable
fun InputTextComponent(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = Int.MAX_VALUE,
    onValueChange: (String) -> Unit = {},
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            color = Cinza2,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick?.invoke() },
            value = text,
            onValueChange = {
                if (it.length <= maxLength) onValueChange(it)
            },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Cinza3,
                focusedBorderColor = Orange,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = Cinza3,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                //For Icons
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            enabled = onClick == null,
            trailingIcon = {
                if (onClick != null) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Cinza,
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun InputTextComponentPreview() {
    ItauCaseBankTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            InputTextComponent(
                title = "Selecione o Banco",
                text = "342 - Itaú",
                onValueChange = {},
                onClick = {},
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = "Digite a conta",
                text = "12345-6",
                onValueChange = {},
            )
        }
    }
}