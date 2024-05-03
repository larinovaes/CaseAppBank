package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.DrawDashLine
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.components.TransferDetailsComponent
import br.com.itaucasebank.presentation.uistate.TransferDetailsUIState
import br.com.itaucasebank.presentation.viewmodel.TransferSharedViewModel
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransferConfirmationScreen(
    navController: NavController,
    viewModel: TransferSharedViewModel = koinViewModel()
) {

    TransferConfirmationScreen(
        transferDetailsUIState = viewModel.transferDetailsUiState.collectAsState().value,
        onConfirmTransfer = { navController.navigate(Route.TRANSFER_RECEIPT.name) },
        onBackClick = { navController.popBackStack() },
    )
}

@Composable
private fun TransferConfirmationScreen(
    transferDetailsUIState: TransferDetailsUIState,
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
        TransferDetailsComponent(transferDetailsUIState)

        DrawDashLine(modifier = Modifier.padding(horizontal = 34.dp))
        Spacer(modifier = Modifier.height(40.dp))

        ButtonPrimaryComponent(
            modifier = Modifier.padding(horizontal = 48.dp),
            text = stringResource(id = R.string.transfer_confirmation_screen),
            roundedCornerSize = 16.dp,
            onClicked = onConfirmTransfer,
        )
    }
}

@Preview
@Composable
private fun TransferConfirmationScreenPreview() {
    val transferDetailsUIState = TransferDetailsUIState()

    ItauCaseBankTheme {
        TransferConfirmationScreen(
            transferDetailsUIState,
            onConfirmTransfer = {},
            onBackClick = {},
        )
    }
}