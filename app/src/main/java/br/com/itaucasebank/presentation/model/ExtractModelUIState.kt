package br.com.itaucasebank.presentation.model

import br.com.itaucasebank.enums.TransactionType

data class ExtractModelUIState (
    val transactionType: TransactionType,
    val nameUser: String,
    val dateAndTime: String,
    val transactionValue: String

)