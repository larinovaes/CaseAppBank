package br.com.itaucasebank.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.itaucasebank.ui.theme.Cinza
import br.com.itaucasebank.ui.theme.Cinza2
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.Purple40

data class SelectOptionUiState(
    val id: String,
    val text: String,
)

@Composable
fun SelectOptionDialogComponent(
    title: String,
    selectedOption: SelectOptionUiState?,
    selectOptionList: List<SelectOptionUiState>,
    onSelectedOption: (SelectOptionUiState) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                TopSection(
                    title = title,
                    onCloseClick = { onDismissRequest() },
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(selectOptionList) {
                        OptionItem(
                            isSelected = selectedOption?.id == it.id,
                            selectOption = it,
                            onClick = {
                                onSelectedOption(it)
                                onDismissRequest()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun TopSection(
    title: String,
    onCloseClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextComponent(
            text = title,
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 32.dp)
                .align(Alignment.Center),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onCloseClick,
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Cinza,
            )
        }
    }
}

@Composable
private fun OptionItem(
    selectOption: SelectOptionUiState,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val textColor = if (isSelected) Purple40 else Cinza2
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        TextComponent(
            text = selectOption.text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
        )
        Spacer(modifier = Modifier.weight(1F))
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Purple40,
            )
        }
    }
}

@Preview
@Composable
private fun SelectOptionDialogComponentPreview() {
    val selectOptionList = listOf(
        SelectOptionUiState(
            id = "1",
            text = "123 - Banco do Brasil",
        ),
        SelectOptionUiState(
            id = "2",
            text = "222 - Nubank",
        ),
        SelectOptionUiState(
            id = "3",
            text = "342 - Itaú Unibanco",
        )
    )
    ItauCaseBankTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            SelectOptionDialogComponent(
                title = "Selecione o tipo de Transferência",
                selectedOption = selectOptionList.first(),
                selectOptionList = selectOptionList,
                onDismissRequest = {},
                onSelectedOption = {},
            )
        }
    }
}