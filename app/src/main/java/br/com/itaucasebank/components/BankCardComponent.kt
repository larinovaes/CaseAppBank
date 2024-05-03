package br.com.itaucasebank.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.R
import br.com.itaucasebank.ui.theme.Blue
import br.com.itaucasebank.ui.theme.Brown
import br.com.itaucasebank.ui.theme.Orange

@Composable
fun BankCardComponent(
    nameUser: String,
    balanceValue: String,
    accountStatementButton: () -> Unit
) {
    val bankCardAspectRatio = 1.590f
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(bankCardAspectRatio),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box {
            BankCardBackground(baseColor = Orange)
            SessionNameUser(nameUser = nameUser)
            StatementButton(accountStatementButton)
            SessionBalance(
                balanceValue,
            )
        }
    }
}

@Composable
private fun SessionNameUser(nameUser: String) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp)
    ) {
        TextComponent(
            text = nameUser,
            fontSize = 24.sp,
            color = Color.White,
        )
    }
}

@Composable
private fun SessionBalance(balanceValue: String) {
    Column(
        modifier = Modifier
            .padding(top = 104.dp, start = 24.dp)
    ) {
        TextComponent(
            text = stringResource(id = R.string.home_screen_text_balance),
            color = Color.White,
            fontWeight = FontWeight.Medium,
        )
        TextComponent(
            text = balanceValue,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun StatementButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 180.dp, start = 24.dp),
    ) {
        TextComponent(
            modifier = Modifier
                .clickable { onClick() },
            text = stringResource(id = R.string.home_screen_text_statement_button),
            color = Color.White,
            fontWeight = FontWeight.Medium,
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.White,
        )
    }

}

@Composable
fun BankCardBackground(baseColor: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(baseColor)
    ) {
        drawCircle(
            color = Blue,
            center = Offset(x = size.width * 0.1f, y = size.height * 0.6f),
            radius = size.minDimension * 0.75f,
        )

        drawCircle(
            color = Brown,
            center = Offset(x = size.width * 0.9f, y = size.height * 0.1f),
            radius = size.minDimension * 0.5f
        )
    }
}

@Composable
@Preview
fun BankCardUiPreview() {
    Box(Modifier.padding(16.dp)) {
        BankCardComponent(
            nameUser = "Carlos Daniel",
            balanceValue = "R\$ 3.450,00",
            accountStatementButton = {}
        )
    }
}