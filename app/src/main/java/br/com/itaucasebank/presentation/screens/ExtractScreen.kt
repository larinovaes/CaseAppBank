package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ExtractComponent
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.enums.TransactionType
import br.com.itaucasebank.presentation.model.ExtractModelUIState
import br.com.itaucasebank.ui.theme.ItaucasebankTheme

@Composable
fun ExtractScreen(
    navController: NavController,
) {
    ExtractScreen(
        title = stringResource(id = R.string.extract_screen_title_toolbar),
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
private fun ExtractScreen(
    title: String,
    onBackClick: () -> Unit = {}
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        ToolbarComponent(
            title = title,
            onClick = onBackClick
        )

        SectionExtract(
            extractModel = listOf(
                ExtractModelUIState(
                    transactionType = TransactionType.PIX,
                    nameUser = "Maria",
                    transactionValue = "R\$ 1.000,00",
                    dateAndTime = "25/05/2024, 10:15 am",
                ),
                ExtractModelUIState(
                    transactionType = TransactionType.DOC,
                    nameUser = "Maria",
                    transactionValue = "R\$ 1.000,00",
                    dateAndTime = "25/05/2024, 10:15 am",
                ),
                ExtractModelUIState(
                    transactionType = TransactionType.BOLETO,
                    nameUser = "ComGÁS SP",
                    transactionValue = "R\$ 1.000,00",
                    dateAndTime = "25/05/2024, 10:15 am",
                ),
                ExtractModelUIState(
                    transactionType = TransactionType.BOLETO,
                    nameUser = "ComGÁS SP",
                    transactionValue = "R\$ 1.000,00",
                    dateAndTime = "25/05/2024, 10:15 am",
                ),
                ExtractModelUIState(
                    transactionType = TransactionType.BOLETO,
                    nameUser = "ComGÁS SP",
                    transactionValue = "R\$ 1.000,00",
                    dateAndTime = "25/05/2024, 10:15 am",
                ),
                ExtractModelUIState(
                    transactionType = TransactionType.BOLETO,
                    nameUser = "ComGÁS SP",
                    transactionValue = "R\$ 1.000,00",
                    dateAndTime = "25/05/2024, 10:15 am",
                ),
                ExtractModelUIState(
                    transactionType = TransactionType.BOLETO,
                    nameUser = "ComGÁS SP",
                    transactionValue = "R\$ 1.000,00",
                    dateAndTime = "25/05/2024, 10:15 am",
                )
            )
        )

    }
}

@Composable
private fun SectionExtract(
    extractModel: List<ExtractModelUIState>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            items(extractModel) {
                ExtractComponent(it)
            }
        }
    }
}

@Preview
@Composable
private fun ExtractPreview() {
    ItaucasebankTheme {
        ExtractScreen(
            title = "Extrato"
        )
    }
}