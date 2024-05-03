package br.com.itaucasebank.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import br.com.itaucasebank.core.formatToHour
import br.com.itaucasebank.core.formatToSimpleDate
import br.com.itaucasebank.enums.BankType
import br.com.itaucasebank.enums.TransactionType
import br.com.itaucasebank.presentation.uistate.ContactUIState
import br.com.itaucasebank.presentation.uistate.TransferDetailsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class TransferSharedViewModel(
    private val application: Application
) : ViewModel() {

    private val _transferAreaUiState = MutableStateFlow(TransferAreaUIState())
    val transferAreaUiState = _transferAreaUiState.asStateFlow()

    private val _transferDetailsUiState = MutableStateFlow(TransferDetailsUIState())
    val transferDetailsUiState = _transferDetailsUiState.asStateFlow()

    fun setTransferDetailsUiState() {
        val agency = _transferAreaUiState.value.selectedBankType.code + " - " +
                application.getString(_transferAreaUiState.value.selectedBankType.description)
        _transferDetailsUiState.update {
            it.copy(
                name = _transferAreaUiState.value.recipientName,
                cpf = _transferAreaUiState.value.recipientCpf,
                accountNumber = _transferAreaUiState.value.account,
                agency = agency,
                transferValue = _transferAreaUiState.value.transferValue,
                date = Date().formatToSimpleDate(),
                hour = Date().formatToHour()
            )
        }
    }

    fun setSelectedBankType(bankType: BankType) {
        _transferAreaUiState.update { it.copy(selectedBankType = bankType) }
        setEnableButton()
    }

    fun setSelectedTransferType(transactionType: TransactionType) {
        _transferAreaUiState.update { it.copy(selectedTransferType = transactionType) }
        setEnableButton()
    }

    fun setIsSaveContactChecked(isSaveContactChecked: Boolean) {
        _transferAreaUiState.update { it.copy(isSaveContactChecked = isSaveContactChecked) }
    }

    fun setAccountInputTextValueChanged(account: String) {
        _transferAreaUiState.update { it.copy(account = account) }
        setEnableButton()
    }

    fun setRecipientInputTextValueChanged(recipient: String) {
        _transferAreaUiState.update { it.copy(recipientName = recipient) }
        setEnableButton()
    }

    fun setRecipientCpfInputTextValueChanged(recipientCpf: String) {
        _transferAreaUiState.update { it.copy(recipientCpf = recipientCpf) }
        setEnableButton()
    }

    fun setTransferValueInputTextValueChanged(transferValue: String) {
        _transferAreaUiState.update { it.copy(transferValue = transferValue) }
        setEnableButton()
    }

    fun setMessageInputTextValueChanged(message: String) {
        _transferAreaUiState.update { it.copy(message = message) }
        setEnableButton()
    }

    private fun setEnableButton() {
        val shouldEnableButton = _transferAreaUiState.value.account.isNotEmpty() &&
                _transferAreaUiState.value.transferValue.isNotEmpty() &&
                _transferAreaUiState.value.recipientName.isNotEmpty() &&
                _transferAreaUiState.value.recipientCpf.isNotEmpty() &&
                _transferAreaUiState.value.selectedBankType != BankType.NONE &&
                _transferAreaUiState.value.selectedTransferType != TransactionType.NONE
        _transferAreaUiState.update { it.copy(isButtonEnabled = shouldEnableButton) }
    }

    data class TransferAreaUIState(
        val contactUiStates: List<ContactUIState> = getMockedContacts(),
        val account: String = "",
        val recipientName: String = "",
        val recipientCpf: String = "",
        val transferValue: String = "",
        val message: String = "",
        val selectedBankType: BankType = BankType.NONE,
        val selectedTransferType: TransactionType = TransactionType.NONE,
        val isSaveContactChecked: Boolean = false,
        val isButtonEnabled: Boolean = false,
        val isCPFErrorVisible: Boolean = false,
    ) {

        companion object {
            private fun getMockedContacts(): List<ContactUIState> {
               return listOf(
                    ContactUIState(
                        id = "1",
                        profileImageUrl = "https://media.licdn.com/dms/image/D4D03AQEIBryHBC4dKw/profile-displayphoto-shrink_400_400/0/1696031035294?e=1720051200&v=beta&t=fqLQ--hMjKeYcrIGUKn_TYmMsTbFh_eQcbugRY-cqos",
                        name = "Larissa",
                    )
                )
            }
        }

    }
}