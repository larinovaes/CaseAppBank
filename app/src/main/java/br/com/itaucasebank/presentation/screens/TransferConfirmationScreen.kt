package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.components.TransferDetailsComponent
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.ItaucasebankTheme

@Composable
fun TransferConfirmationScreen(
    navController: NavController,
) {
    TransferConfirmationScreen(
        name = "Maria Ernesta", // todo: Recuperar da ViewModel
        cpf = "123.456.789-11", // todo: Recuperar da ViewModel
        agency = "342 - Nubank", // todo: Recuperar da ViewModel
        accountNumber = "12345-7", // todo: Recuperar da ViewModel
        date = "18/04/2025", // todo: Recuperar da ViewModel
        hour = "10:26 am", // todo: Recuperar da ViewModel
        transferValue = "R$ 520,00", // todo: Recuperar da ViewModel
        onConfirmTransfer = { navController.navigate(Route.TRANSFER_RECEIPT.name) },
        onBackClick = { navController.popBackStack() },
    )
}

@Composable
private fun TransferConfirmationScreen(
    name: String,
    cpf: String,
    agency: String,
    accountNumber: String,
    date: String,
    hour: String,
    transferValue: String,
    onBackClick: () -> Unit,
    onConfirmTransfer: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ToolbarComponent(
            title = stringResource(id = R.string.transfer_area_screen_title_toolbar),
            onClick = onBackClick
        )
        Spacer(modifier = Modifier.height(24.dp))
        TransferDetailsComponent(
            name = name,
            cpf = cpf,
            agency = agency,
            accountNumber = accountNumber,
            date = date,
            hour = hour,
            transferValue = transferValue,
        )
        Spacer(modifier = Modifier.height(40.dp))
        ButtonPrimaryComponent(
            modifier = Modifier.padding(horizontal = 48.dp),
            text = "Confirmar Transferência",
            roundedCornerSize = 16.dp,
            onClicked = onConfirmTransfer,
        )
    }
}

@Preview
@Composable
private fun TransferConfirmationScreenPreview() {
    ItaucasebankTheme {
        TransferConfirmationScreen(
            name = "Maria Vieira",
            cpf = "123.456.789-10",
            agency = "342 - Itaú Unibanco",
            accountNumber = "12345-6",
            date = "18/04/2024",
            hour = "10:25 am",
            transferValue = "R$ 500,00",
            onConfirmTransfer = {},
            onBackClick = {},
        )
    }
}