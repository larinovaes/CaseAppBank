package br.com.itaucasebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.itaucasebank.core.formatToAccountNumber
import br.com.itaucasebank.core.formatToCurrency
import br.com.itaucasebank.domain.model.UserAndAccountDetailsModel
import br.com.itaucasebank.domain.usecase.GetUserAndAccountDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserAndAccountDetailsUseCase: GetUserAndAccountDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        getUserAndAccountDetailsUseCase.execute()
            .onSuccess { handleSuccess(it) }
            .onFailure { _uiState.update { it.copy(isLoading = false, isError = true) } }
    }

    private fun handleSuccess(userAndAccountDetailsModel: UserAndAccountDetailsModel) {
        _uiState.update {
            it.copy(
                userName = userAndAccountDetailsModel.userName,
                profileImageUrl = userAndAccountDetailsModel.profileImageUrl,
                accountNumber = userAndAccountDetailsModel.accountNumber.formatToAccountNumber(),
                agencyNumber = userAndAccountDetailsModel.agencyNumber,
                balanceValue = userAndAccountDetailsModel.balanceValue.formatToCurrency(),
                isLoading = false,
                isError = false,
            )
        }
    }

    data class UiState(
        val userName: String = "",
        val profileImageUrl: String = "",
        val accountNumber: String = "",
        val agencyNumber: String = "",
        val balanceValue: String = "",
        val notificationCount: Int = 3,
        val isLoading: Boolean = true,
        val isError: Boolean = false,
    )
}
