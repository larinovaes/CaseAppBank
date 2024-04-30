package br.com.itaucasebank.data.remote.dto

import com.squareup.moshi.Json

data class PaymentDto(
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "category")
    val category: String,

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "amount")
    val amount: String,
)