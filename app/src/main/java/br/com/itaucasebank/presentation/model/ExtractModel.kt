package br.com.itaucasebank.presentation.model

import br.com.itaucasebank.enums.TransactionType

data class ExtractModel(
    val transactionType: TransactionType,
    val nameUser: String,
    val dateAndTime: String,
    val transactionValue: String,
)
