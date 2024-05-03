package br.com.itaucasebank.presentation.uistate

data class TransferDetailsUIState(
    val name: String = "",
    val cpf: String = "",
    val agency: String = "",
    val accountNumber: String = "",
    val date: String = "",
    val hour: String = "",
    val transferValue: String = "",
)