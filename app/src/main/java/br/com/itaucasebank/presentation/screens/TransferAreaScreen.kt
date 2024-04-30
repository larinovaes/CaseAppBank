package br.com.itaucasebank.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.ContactCardComponent
import br.com.itaucasebank.components.InputTextComponent
import br.com.itaucasebank.components.SelectOptionDialogComponent
import br.com.itaucasebank.components.SelectOptionUiState
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.enums.BankType
import br.com.itaucasebank.enums.TransactionType
import br.com.itaucasebank.presentation.uistate.ContactUIState
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.Cinza
import br.com.itaucasebank.ui.theme.ItaucasebankTheme
import br.com.itaucasebank.ui.theme.Purple40

@Composable
fun TransferAreaScreen(
    navController: NavController,
) {
    val contactUIStates = listOf(
        ContactUIState(
            id = "1",
            profileImageUrl = "https://media.licdn.com/dms/image/D4D03AQEIBryHBC4dKw/profile-displayphoto-shrink_400_400/0/1696031035294?e=1720051200&v=beta&t=fqLQ--hMjKeYcrIGUKn_TYmMsTbFh_eQcbugRY-cqos",
            name = "Larissa",
        )
    )
    TransferAreaScreen(
        contactUIStates = contactUIStates, // todo: Recuperar da ViewModel
        accountInputTextValue = "", // todo: Recuperar da ViewModel
        recipientInputTextValue = "", // todo: Recuperar da ViewModel
        recipientCpfInputTextValue = "", // todo: Recuperar da ViewModel
        transferValueInputTextValue = "",  // todo: Recuperar da ViewModel
        messageInputTextValue = "", // todo: Recuperar da ViewModel
        selectedBankType = BankType.BB, // todo: Recuperar da ViewModel
        selectedTransferType = TransactionType.PIX, // todo: Recuperar da ViewModel
        isSaveContactChecked = true, // todo: Recuperar da ViewModel
        onSaveContactCheckedChange = {},
        onBackClick = { navController.popBackStack() },
        onAddNewContactClick = {},
        onContactClick = {},
        onSelectedBankType = {}, // todo: Chamar ViewModel
        onSelectedTransferType = {}, // todo: Chamar ViewModel
        onNextClick = { navController.navigate(Route.TRANSFER_CONFIRMATION.name) },
    )
}

@Composable
private fun TransferAreaScreen(
    contactUIStates: List<ContactUIState>,
    accountInputTextValue: String,
    recipientInputTextValue: String,
    recipientCpfInputTextValue: String,
    transferValueInputTextValue: String,
    messageInputTextValue: String,
    selectedBankType: BankType?,
    selectedTransferType: TransactionType?,
    isSaveContactChecked: Boolean,
    onSaveContactCheckedChange: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onAddNewContactClick: () -> Unit,
    onContactClick: () -> Unit,
    onSelectedBankType: (BankType) -> Unit,
    onSelectedTransferType: (TransactionType) -> Unit,
    onNextClick: () -> Unit,
) {
    val shouldOpenBankTypeDialog = remember { mutableStateOf(false) }
    val shouldOpenTransferTypeDialog = remember { mutableStateOf(false) }
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            ToolbarComponent(
                title = stringResource(id = R.string.transfer_area_screen_title_toolbar),
                onClick = onBackClick
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
            ) {
                AccountSection(
                    onClick = onAddNewContactClick,
                )
                ContactSection(
                    onClick = onContactClick,
                    contactUIStates = contactUIStates,
                )
                InputTextSection(
                    accountInputTextValue = accountInputTextValue,
                    recipientInputTextValue = recipientInputTextValue,
                    recipientCpfInputTextValue = recipientCpfInputTextValue,
                    transferValueInputTextValue = transferValueInputTextValue,
                    messageInputTextValue = messageInputTextValue,
                    selectedBankType = selectedBankType,
                    selectedTransferType = selectedTransferType,
                    isSaveContactChecked = isSaveContactChecked,
                    onSaveContactCheckedChange = onSaveContactCheckedChange,
                    onNextClick = onNextClick,
                    openBankTypeSelectOptionDialog = {
                        shouldOpenBankTypeDialog.value = true
                    },
                    openTransferTypeSelectOptionDialog = {
                        shouldOpenTransferTypeDialog.value = true
                    },
                )
            }
        }
        BankTypeSelectOptionDialog(
            selectedBankType = selectedBankType,
            openAlertDialog = shouldOpenBankTypeDialog,
            onSelectedOption = onSelectedBankType,
        )
        TransferTypeSelectOptionDialog(
            selectedTransferType = selectedTransferType,
            openAlertDialog = shouldOpenTransferTypeDialog,
            onSelectedOption = onSelectedTransferType,
        )
    }
}

@Composable
private fun ContactSection(
    onClick: () -> Unit,
    contactUIStates: List<ContactUIState>
) {
    LazyRow {
        items(contactUIStates) {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp)
            ) {
                ContactCardComponent(
                    onClick = { onClick() },
                    contacts = it
                )
            }
        }
    }
}

@Composable
private fun AccountSection(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.transfer_area_screen_title_account_session),
            color = Cinza,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier
                .clickable { onClick() },
            text = stringResource(id = R.string.transfer_area_screen_button_account_session),
            color = Purple40,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun InputTextSection(
    accountInputTextValue: String,
    recipientInputTextValue: String,
    recipientCpfInputTextValue: String,
    transferValueInputTextValue: String,
    messageInputTextValue: String,
    selectedBankType: BankType?,
    selectedTransferType: TransactionType?,
    isSaveContactChecked: Boolean,
    onSaveContactCheckedChange: (Boolean) -> Unit,
    openBankTypeSelectOptionDialog: () -> Unit,
    openTransferTypeSelectOptionDialog: () -> Unit,
    onNextClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 24.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            InputTextComponent(
                title = "Selecione o Banco",
                text = selectedBankType.getTextValue(),
                onClick = openBankTypeSelectOptionDialog,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = "Digite a conta",
                text = accountInputTextValue,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = "Nome do recebedor",
                text = recipientInputTextValue,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = "CPF do recebedor",
                text = recipientCpfInputTextValue,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = "Valor da Transfência",
                text = transferValueInputTextValue,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = "Tipo de Transferência",
                text = selectedTransferType.getTextValue(),
                onClick = openTransferTypeSelectOptionDialog,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = "Mensagem",
                text = messageInputTextValue,
            )
            Spacer(modifier = Modifier.height(40.dp))
            SaveContactCheckBox(
                isSaveContactChecked = isSaveContactChecked,
                onSaveContactCheckedChange = onSaveContactCheckedChange,
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimaryComponent(
                text = "Próximo",
                onClicked = onNextClick,
                roundedCornerSize = 16.dp,
            )
        }
    }
}

@Composable
private fun BankTypeSelectOptionDialog(
    selectedBankType: BankType?,
    openAlertDialog: MutableState<Boolean>,
    onSelectedOption: (BankType) -> Unit,
) {
    val selectOptionList = BankType.entries
        .map {
            SelectOptionUiState(
                id = it.name,
                text = it.getTextValue(),
            )
        }
    if (openAlertDialog.value) {
        SelectOptionDialogComponent(
            title = "Selecione um banco",
            selectedOption = selectOptionList.first { it.id == selectedBankType?.name },
            selectOptionList = selectOptionList,
            onDismissRequest = { openAlertDialog.value = false },
            onSelectedOption = { option ->
                val selectedOption = BankType.entries.find { it.name == option.id }
                onSelectedOption(selectedOption ?: return@SelectOptionDialogComponent)
            },
        )
    }
}

@Composable
private fun TransferTypeSelectOptionDialog(
    selectedTransferType: TransactionType?,
    openAlertDialog: MutableState<Boolean>,
    onSelectedOption: (TransactionType) -> Unit,
) {
    val selectOptionList = TransactionType.entries
        .filter { it != TransactionType.BOLETO }
        .map {
            SelectOptionUiState(
                id = it.name,
                text = it.getTextValue(),
            )
        }
    if (openAlertDialog.value) {
        SelectOptionDialogComponent(
            title = "Selecione o tipo de Transferência",
            selectedOption = selectOptionList.first { it.id == selectedTransferType?.name },
            selectOptionList = selectOptionList,
            onDismissRequest = { openAlertDialog.value = false },
            onSelectedOption = { option ->
                val selectedOption = TransactionType.entries.find { it.name == option.id }
                onSelectedOption(selectedOption ?: return@SelectOptionDialogComponent)
            },
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SaveContactCheckBox(
    isSaveContactChecked: Boolean,
    onSaveContactCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Checkbox(
                checked = isSaveContactChecked,
                onCheckedChange = onSaveContactCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Purple40,
                )
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = "Salvar contato para novas transferências",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Purple40,
        )
    }
}

@Composable
private fun BankType?.getTextValue(): String {
    return if (this != null) this.code + " - " + stringResource(id = this.description)
    else ""
}

@Composable
private fun TransactionType?.getTextValue(): String {
    return if (this != null) stringResource(id = this.title)
    else ""
}

@Preview
@Composable
private fun TransferAreaPreview() {
    val contactUIStates = listOf(
        ContactUIState(
            id = "1",
            profileImageUrl = "https://media.licdn.com/dms/image/D4D03AQEIBryHBC4dKw/profile-displayphoto-shrink_400_400/0/1696031035294?e=1720051200&v=beta&t=fqLQ--hMjKeYcrIGUKn_TYmMsTbFh_eQcbugRY-cqos",
            name = "Larissa",
        )
    )
    val selectedBankType = remember { mutableStateOf(BankType.BB) }
    val selectedTransferType = remember { mutableStateOf(TransactionType.PIX) }
    ItaucasebankTheme {
        TransferAreaScreen(
            contactUIStates = contactUIStates,
            accountInputTextValue = "12345-6",
            recipientInputTextValue = "Maria Vieira",
            recipientCpfInputTextValue = "123.456.789-10",
            transferValueInputTextValue = "R$ 500,00",
            messageInputTextValue = "Pagamento do Jantar ",
            selectedBankType = selectedBankType.value,
            selectedTransferType = selectedTransferType.value,
            isSaveContactChecked = true,
            onSaveContactCheckedChange = {},
            onBackClick = {},
            onAddNewContactClick = {},
            onContactClick = {},
            onSelectedBankType = { selectedBankType.value = it },
            onSelectedTransferType = { selectedTransferType.value = it },
            onNextClick = {},
        )
    }
}
