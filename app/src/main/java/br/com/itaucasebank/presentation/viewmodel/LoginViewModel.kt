package br.com.itaucasebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.itaucasebank.domain.usecase.AuthenticateUserUseCase
import br.com.itaucasebank.domain.exception.InvalidEmailOrPasswordException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authenticateUserUseCase: AuthenticateUserUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun setEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun setPassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun authenticate() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, isCredentialErrorVisible = false) }
        authenticateUserUseCase.execute(_uiState.value.email, _uiState.value.password)
            .onSuccess { _uiEvent.emit(UiEvent.NavigateToHome) }
            .onFailure { handleError(it) }
    }

    private fun handleError(exception: Throwable) {
        when (exception) {
            is InvalidEmailOrPasswordException ->
                _uiState.update { it.copy(isLoading = false, isCredentialErrorVisible = true) }
        }
    }

    data class UiState(
        val email: String = "Crystal_Fisher@hotmail.com",
        val password: String = "A12345",
        val isLoading: Boolean = false,
        val isCredentialErrorVisible: Boolean = false,
    )

    sealed class UiEvent {
        data object NavigateToHome: UiEvent()
    }
}