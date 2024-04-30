package br.com.itaucasebank.domain.model

data class UserModel(
    val id: String,
    val name: String,
    val avatar: String,
    val email: String,
    val password: String,
    val createdAt: String,
)
