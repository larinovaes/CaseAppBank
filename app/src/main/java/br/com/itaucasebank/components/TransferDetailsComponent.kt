package br.com.itaucasebank.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.ui.theme.Cinza2
import br.com.itaucasebank.ui.theme.Cinza3
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Pink

@Composable
fun TransferDetailsComponent(
    name: String,
    cpf: String,
    agency: String,
    accountNumber: String,
    date: String,
    hour: String,
    transferValue: String,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            DetailsRow(
                title = "Nome",
                value = name,
            )
            DetailsRow(
                title = "CPF",
                value = cpf,
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailsRow(
                title = "Agência",
                value = agency,
            )
            DetailsRow(
                title = "Conta",
                value = accountNumber,
            )
            DetailsRow(
                title = "Data",
                value = date,
            )
            DetailsRow(
                title = "Hora",
                value = hour,
            )
            Spacer(modifier = Modifier.height(64.dp))
            DrawDashLine()
            TransferValueRow(transferValue = transferValue)
        }
    }
}

@Composable
fun DrawDashLine(
    modifier: Modifier = Modifier
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
    Canvas(
        modifier.fillMaxWidth()
    ) {
        drawLine(
            color = Cinza3,
            strokeWidth = 5f,
            start = Offset(20f, 0f),
            end = Offset(size.width - 20, 0f),
            pathEffect = pathEffect
        )
    }
}

@Composable
private fun DetailsRow(
    title: String,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Cinza2,
        )
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )
    }
}

@Composable
private fun TransferValueRow(transferValue: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "VALOR",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = transferValue,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Pink,
        )
    }
}

@Preview
@Composable
private fun TransferDetailsComponentPreview() {
    ItaucasebankTheme {
        TransferDetailsComponent(
            name = "Maria Vieira",
            cpf = "123.456.789-10",
            agency = "342 - Itaú Unibanco",
            accountNumber = "12345-6",
            date = "18/04/2024",
            hour = "10:25 am",
            transferValue = "R$ 500,00"
        )
    }
}