package br.com.itaucasebank.domain.model

data class UserAndAccountDetailsModel(
    val userName: String,
    val profileImageUrl: String,
    val accountNumber: String,
    val agencyNumber: String,
    val balanceValue: String,
)
