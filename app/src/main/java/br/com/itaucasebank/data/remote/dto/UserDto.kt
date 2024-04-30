package br.com.itaucasebank.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "avatar")
    val avatar: String,

    @Json(name = "createdAt")
    val createdAt: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "password")
    val password: String
)
