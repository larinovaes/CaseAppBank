package br.com.itaucasebank.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import br.com.itaucasebank.R
class HomeViewModel: ViewModel() {

//    private val _uiState = MutableStateFlow(getInitialUiState())
//    val uiState = _uiState.asStateFlow()


//    private fun getInitialUiState(): UiState {
//        return UiState()
//    }

    data class UiState(
        @DrawableRes val profileImage: Int = R.drawable.ic_icon_person,
        val nameUser: String = "",
        val numberAccount: String,
        val isNotifications: Boolean = false
    )
}
