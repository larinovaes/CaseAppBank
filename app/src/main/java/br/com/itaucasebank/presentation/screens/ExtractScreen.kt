package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ErrorMessageComponent
import br.com.itaucasebank.components.ExtractComponent
import br.com.itaucasebank.components.LoadingComponent
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.presentation.uistate.ExtractUIState
import br.com.itaucasebank.presentation.viewmodel.ExtractViewModel
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExtractScreen(
    navController: NavController,
    viewModel: ExtractViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    ExtractScreen(
        title = stringResource(id = R.string.extract_screen_title_toolbar),
        onBackClick = { navController.popBackStack() },
        extract = uiState.value.extractList,
        isLoading = uiState.value.isLoading,
        isError = uiState.value.isError,
        onClickRetry = { viewModel.getExtracts() }
    )
}

@Composable
private fun ExtractScreen(
    title: String,
    isLoading: Boolean,
    isError: Boolean,
    onClickRetry: () -> Unit,
    onBackClick: () -> Unit = {},
    extract: List<ExtractUIState>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        ToolbarComponent(
            title = title,
            onClick = onBackClick
        )

        if (isLoading) LoadingComponent()
        else if (isError) ErrorMessageComponent(onClick = onClickRetry)
        else SectionExtract(extractUIState = extract)

    }
}

@Composable
private fun SectionExtract(
    extractUIState: List<ExtractUIState>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            items(extractUIState) {
                ExtractComponent(it)
            }
        }
    }
}

@Preview
@Composable
private fun ExtractPreview() {
    val paymentList = listOf(
        ExtractUIState(
            id = "1",
            name = "Maria",
            category = "PIX",
            amount = "R\$ 1.000,00",
            createdAt = "25/05/2024, 10:15 am",
        ),
        ExtractUIState(
            id = "1",
            name = "Maria",
            category = "PIX",
            amount = "R\$ 1.000,00",
            createdAt = "25/05/2024, 10:15 am",
        ),
    )
    ItaucasebankTheme {
        ExtractScreen(
            title = "Extrato",
            onBackClick = { },
            extract = paymentList,
            isLoading = false,
            isError = false,
            onClickRetry = {}
        )
    }
}