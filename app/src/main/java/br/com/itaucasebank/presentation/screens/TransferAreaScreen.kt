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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import br.com.itaucasebank.R
import br.com.itaucasebank.components.ButtonPrimaryComponent
import br.com.itaucasebank.components.ContactCardComponent
import br.com.itaucasebank.ui.transformations.CurrencyTransformation
import br.com.itaucasebank.components.InputTextComponent
import br.com.itaucasebank.ui.transformations.MaskTransformation
import br.com.itaucasebank.ui.transformations.MaskType
import br.com.itaucasebank.components.SelectOptionDialogComponent
import br.com.itaucasebank.components.SelectOptionUiState
import br.com.itaucasebank.components.TextComponent
import br.com.itaucasebank.components.ToolbarComponent
import br.com.itaucasebank.enums.BankType
import br.com.itaucasebank.enums.TransactionType
import br.com.itaucasebank.presentation.uistate.ContactUIState
import br.com.itaucasebank.presentation.viewmodel.TransferSharedViewModel
import br.com.itaucasebank.router.Route
import br.com.itaucasebank.ui.theme.GrayPrimary
import br.com.itaucasebank.ui.theme.ItauCaseBankTheme
import br.com.itaucasebank.ui.theme.PurplePrimary
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransferAreaScreen(
    navController: NavController,
    viewModel: TransferSharedViewModel = koinViewModel()
) {
    val uiState = viewModel.transferAreaUiState.collectAsState()
    TransferAreaScreen(
        contactUIStates = uiState.value.contactUiStates,
        accountInputTextValue = uiState.value.account,
        recipientInputTextValue = uiState.value.recipientName,
        recipientCpfInputTextValue = uiState.value.recipientCpf,
        transferValueInputTextValue = uiState.value.transferValue,
        messageInputTextValue = uiState.value.message,
        selectedBankType = uiState.value.selectedBankType,
        selectedTransferType = uiState.value.selectedTransferType,
        isSaveContactChecked = uiState.value.isSaveContactChecked,
        isEnabled = uiState.value.isButtonEnabled,
        onSaveContactCheckedChange = { viewModel.setIsSaveContactChecked(it) },
        onBackClick = { navController.popBackStack() },
        onAddNewContactClick = {},
        onContactClick = {},
        onSelectedBankType = { viewModel.setSelectedBankType(it) },
        onSelectedTransferType = { viewModel.setSelectedTransferType(it) },
        onNextClick = {
            viewModel.setTransferDetailsUiState()
            navController.navigate(Route.TRANSFER_CONFIRMATION.name)
        },
        onAccountInputTextValueChanged = { viewModel.setAccountInputTextValueChanged(it) },
        onRecipientInputTextValueChanged = { viewModel.setRecipientInputTextValueChanged(it) },
        onRecipientCpfInputTextValueChanged = { viewModel.setRecipientCpfInputTextValueChanged(it) },
        transferValueInputTextValueChanged = { viewModel.setTransferValueInputTextValueChanged(it) },
        messageInputTextValueChanged = { viewModel.setMessageInputTextValueChanged(it) }
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
    isEnabled: Boolean,
    onAccountInputTextValueChanged: (String) -> Unit,
    onRecipientInputTextValueChanged: (String) -> Unit,
    onRecipientCpfInputTextValueChanged: (String) -> Unit,
    transferValueInputTextValueChanged: (String) -> Unit,
    messageInputTextValueChanged: (String) -> Unit,
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
                    onAccountInputTextValueChanged = onAccountInputTextValueChanged,
                    onRecipientInputTextValueChanged = onRecipientInputTextValueChanged,
                    onRecipientCpfInputTextValueChanged =  onRecipientCpfInputTextValueChanged,
                    transferValueInputTextValueChanged = transferValueInputTextValueChanged,
                    messageInputTextValueChanged = messageInputTextValueChanged,
                    isEnabled = isEnabled,
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
        TextComponent(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.transfer_area_screen_title_account_session),
            color = GrayPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        TextComponent(
            modifier = Modifier
                .clickable { onClick() },
            text = stringResource(id = R.string.transfer_area_screen_button_account_session),
            color = PurplePrimary,
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
    isEnabled: Boolean,
    onAccountInputTextValueChanged: (String) -> Unit,
    onRecipientInputTextValueChanged:(String) -> Unit,
    onRecipientCpfInputTextValueChanged:(String) -> Unit,
    transferValueInputTextValueChanged:(String) -> Unit,
    messageInputTextValueChanged:(String) -> Unit,
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
                title = stringResource(id = R.string.transfer_area_screen_select_bank),
                text = selectedBankType.getTextValue(),
                onClick = openBankTypeSelectOptionDialog,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = stringResource(id = R.string.transfer_area_screen_input_account),
                text = accountInputTextValue,
                maxLength = 6,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
                visualTransformation = MaskTransformation(MaskType.ACCOUNT_NUMBER_MASK),
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        onAccountInputTextValueChanged.invoke(it)
                    }
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = stringResource(id = R.string.transfer_area_screen_receipt_name),
                text = recipientInputTextValue,
                onValueChange = onRecipientInputTextValueChanged,
            )

            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = stringResource(id = R.string.transfer_area_screen_receipt_cpf),
                text = recipientCpfInputTextValue,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
                maxLength = 11,
                visualTransformation = MaskTransformation(MaskType.CPF_MASK),
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        onRecipientCpfInputTextValueChanged.invoke(it)
                    }
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                visualTransformation = CurrencyTransformation(),
                title = stringResource(id = R.string.transfer_area_screen_transfer_value),
                text = transferValueInputTextValue,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
                maxLength = 7,
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        transferValueInputTextValueChanged.invoke(it)
                    }
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = stringResource(id = R.string.transfer_area_screen_transfer_type),
                text = selectedTransferType.getTextValue(),
                onClick = openTransferTypeSelectOptionDialog,
            )
            Spacer(modifier = Modifier.height(24.dp))
            InputTextComponent(
                title = stringResource(id = R.string.transfer_area_screen_message),
                text = messageInputTextValue,
                onValueChange = messageInputTextValueChanged
            )
            Spacer(modifier = Modifier.height(40.dp))
            SaveContactCheckBox(
                isSaveContactChecked = isSaveContactChecked,
                onSaveContactCheckedChange = onSaveContactCheckedChange,
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimaryComponent(
                text = stringResource(id = R.string.transfer_area_next_button),
                onClicked = onNextClick,
                roundedCornerSize = 16.dp,
                isEnabled = isEnabled
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
        .filter { it != BankType.NONE }
        .map {
            SelectOptionUiState(
                id = it.name,
                text = it.getTextValue(),
            )
        }
    if (openAlertDialog.value) {
        SelectOptionDialogComponent(
            title = stringResource(id = R.string.transfer_area_screen_select_bank),
            selectedOption = selectOptionList.firstOrNull { it.id == selectedBankType?.name },
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
        .filter { it != TransactionType.NONE }
        .map {
            SelectOptionUiState(
                id = it.name,
                text = it.getTextValue(),
            )
        }
    if (openAlertDialog.value) {
        SelectOptionDialogComponent(
            title = stringResource(id = R.string.transfer_area_screen_title_transfer_type),
            selectedOption = selectOptionList.firstOrNull { it.id == selectedTransferType?.name },
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
                    checkedColor = PurplePrimary,
                )
            )
        }
        TextComponent(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = stringResource(id = R.string.transfer_area_screen_save_contact),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = PurplePrimary,
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
    ItauCaseBankTheme {
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
            isEnabled = true,
            onSaveContactCheckedChange = {},
            onBackClick = {},
            onAddNewContactClick = {},
            onContactClick = {},
            onSelectedBankType = { selectedBankType.value = it },
            onSelectedTransferType = { selectedTransferType.value = it },
            onNextClick = {},
            onAccountInputTextValueChanged = {},
            onRecipientInputTextValueChanged = {},
            onRecipientCpfInputTextValueChanged = {},
            transferValueInputTextValueChanged = {},
            messageInputTextValueChanged = {}
        )
    }
}
