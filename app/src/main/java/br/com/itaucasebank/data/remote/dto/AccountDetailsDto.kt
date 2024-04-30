package br.com.itaucasebank.data.remote.dto

import com.squareup.moshi.Json

data class AccountDetailsDto(
    @Json(name = "id")
    val id: String,

    @Json(name = "userId")
    val userId: String,

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "account")
    val account: String,

    @Json(name = "amount")
    val amount: String,

    @Json(name = "payments")
    val payments: List<PaymentDto>,
)