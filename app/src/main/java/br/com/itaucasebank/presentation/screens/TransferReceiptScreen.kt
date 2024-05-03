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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.DrawDashLine
import br.com.itaucasebank.components.SendReceiptDialogComponent
import br.com.itaucasebank.components.TextComponent
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.components.TransferDetailsComponent
import br.com.itaucasebank.presentation.uistate.TransferDetailsUIState
import br.com.itaucasebank.presentation.viewmodel.TransferSharedViewModel
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Green
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransferReceiptScreen(
    navController: NavController,
    viewModel: TransferSharedViewModel = koinViewModel()
) {
    TransferReceiptScreen(
        transferDetailsUIState = viewModel.transferDetailsUiState.collectAsState().value,
        onBackClick = { navController.popBackStack(Route.HOME.name, inclusive = false) },
    )
}

@Composable
private fun TransferReceiptScreen(
    transferDetailsUIState: TransferDetailsUIState,
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
                title = stringResource(id = R.string.transfer_receipt_screen_toolbar_title),
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
                TextComponent(
                    text = stringResource(id = R.string.transfer_receipt_screen_message_success),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Green,
                )
                Spacer(modifier = Modifier.height(32.dp))
                TransferDetailsComponent(
                    transferDetailsUIState = transferDetailsUIState
                )
                DrawDashLine(
                    modifier = Modifier.padding(horizontal = 34.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                ButtonPrimaryComponent(
                    modifier = Modifier.padding(horizontal = 48.dp),
                    text = stringResource(id = R.string.transfer_receipt_screen_button_text),
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
    val transferDetailsUIState = TransferDetailsUIState()
    ItauCaseBankTheme {
        TransferReceiptScreen(
            transferDetailsUIState = transferDetailsUIState,
            onBackClick = { },
        )
    }
}