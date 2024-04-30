package br.com.itaucasebank.domain

data class PaymentModel(
    val id: String,
    val name: String,
    val category: String,
    val createdAt: String,
    val amount: String,
)