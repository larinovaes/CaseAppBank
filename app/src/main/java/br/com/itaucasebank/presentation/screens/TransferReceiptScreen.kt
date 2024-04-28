package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.SendReceiptDialogComponent
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.components.TransferDetailsComponent
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Green
import br.com.itaucasebank.ui.theme.ItaucasebankTheme

@Composable
fun TransferReceiptScreen(
    navController: NavController,
) {
    TransferReceiptScreen(
        name = "Maria Ernesta", // todo: Recuperar da ViewModel
        cpf = "123.456.789-11", // todo: Recuperar da ViewModel
        agency = "342 - Nubank", // todo: Recuperar da ViewModel
        accountNumber = "12345-7", // todo: Recuperar da ViewModel
        date = "18/04/2025", // todo: Recuperar da ViewModel
        hour = "10:26 am", // todo: Recuperar da ViewModel
        transferValue = "R$ 520,00", // todo: Recuperar da ViewModel
        onBackClick = { navController.popBackStack(Route.HOME.name, inclusive = false) },
    )
}

@Composable
private fun TransferReceiptScreen(
    name: String,
    cpf: String,
    agency: String,
    accountNumber: String,
    date: String,
    hour: String,
    transferValue: String,
    onBackClick: () -> Unit,
) {
    val shouldOpenSendReceiptDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            ToolbarComponent(
                title = "Comprovante",
                onClick = onBackClick,
            )
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    modifier = Modifier
                        .width(264.dp)
                        .height(174.dp),
                    painter = painterResource(id = R.drawable.im_transfer_receipt),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Transferência concluída com sucesso!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Green,
                )
                Spacer(modifier = Modifier.height(32.dp))
                TransferDetailsComponent(
                    name = name,
                    cpf = cpf,
                    agency = agency,
                    accountNumber = accountNumber,
                    date = date,
                    hour = hour,
                    transferValue = transferValue,
                )
                Spacer(modifier = Modifier.height(32.dp))
                ButtonPrimaryComponent(
                    modifier = Modifier.padding(horizontal = 48.dp),
                    text = "Enviar Comprovante",
                    roundedCornerSize = 16.dp,
                    onClicked = { shouldOpenSendReceiptDialog.value = true },
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        if (shouldOpenSendReceiptDialog.value) {
            SendReceiptDialogComponent(
                onDismissRequest = { shouldOpenSendReceiptDialog.value = false }
            )
        }
    }
}

@Preview
@Composable
private fun TransferConfirmationScreenPreview() {
    ItaucasebankTheme {
        TransferReceiptScreen(
            name = "Maria Vieira",
            cpf = "123.456.789-10",
            agency = "342 - Itaú Unibanco",
            accountNumber = "12345-6",
            date = "18/04/2024",
            hour = "10:25 am",
            transferValue = "R$ 500,00",
            onBackClick = { },
        )
    }
}