package br.com.itaucasebank.domain

data class AccountDetailsModel(
    val id: String,
    val userId: String,
    val createdAt: String,
    val account: String,
    val amount: String,
    val payments: List<PaymentModel>,
)

