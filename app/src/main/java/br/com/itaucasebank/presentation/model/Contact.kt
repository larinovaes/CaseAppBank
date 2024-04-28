package br.com.itaucasebank.presentation.model

import androidx.annotation.DrawableRes

data class Contact(
    @DrawableRes val profileImage: Int,
    val nameContact: String
)
