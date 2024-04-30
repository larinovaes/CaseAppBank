package br.com.itaucasebank.domain.model

data class PaymentModel(
    val id: String,
    val name: String,
    val category: String,
    val createdAt: String,
    val amount: String,
)