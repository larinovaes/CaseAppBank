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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itaucasebank.R
import br.com.itaucasebank.core.formatToAccountNumberComponent
import br.com.itaucasebank.core.formatToCurrency
import br.com.itaucasebank.presentation.uistate.TransferDetailsUIState
import br.com.itaucasebank.ui.theme.GraySecondary
import br.com.itaucasebank.ui.theme.GrayTertiary
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.Pink

@Composable
fun TransferDetailsComponent(transferDetailsUIState: TransferDetailsUIState) {
    TransferDetailsComponent(
        name = transferDetailsUIState.name,
        cpf = transferDetailsUIState.cpf,
        agency = transferDetailsUIState.agency,
        accountNumber = transferDetailsUIState.accountNumber.formatToAccountNumberComponent(),
        date = transferDetailsUIState.date,
        hour = transferDetailsUIState.hour,
        transferValue = transferDetailsUIState.transferValue.formatToCurrency()
    )
}

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
                title = stringResource(id = R.string.transfer_details_name),
                value = name,
            )
            DetailsRow(
                title = stringResource(id = R.string.transfer_details_cpf),
                value = cpf,
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailsRow(
                title = stringResource(id = R.string.transfer_details_agency),
                value = agency,
            )
            DetailsRow(
                title = stringResource(id = R.string.transfer_details_account),
                value = accountNumber,
            )
            DetailsRow(
                title = stringResource(id = R.string.transfer_details_date),
                value = date,
            )
            DetailsRow(
                title = stringResource(id = R.string.transfer_details_hour),
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
            color = GrayTertiary,
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
        TextComponent(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = GraySecondary,
        )
        Spacer(modifier = Modifier.weight(1F))
        TextComponent(
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
        TextComponent(
            text = "VALOR",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.weight(1F))
        TextComponent(
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
    ItauCaseBankTheme {
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