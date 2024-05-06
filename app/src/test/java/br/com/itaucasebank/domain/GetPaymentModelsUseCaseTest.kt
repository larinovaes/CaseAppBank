package br.com.itaucasebank.domain

import br.com.itaucasebank.data.repository.AppRepository
import br.com.itaucasebank.domain.model.AccountDetailsModel
import br.com.itaucasebank.domain.model.PaymentModel
import br.com.itaucasebank.domain.model.UserModel
import br.com.itaucasebank.domain.usecase.GetPaymentModelsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPaymentModelsUseCaseTest {

    private val mockAppRepository = mockk<AppRepository>()

    private lateinit var getPaymentModelsUseCase: GetPaymentModelsUseCase

    @Before
    fun setup() {
        getPaymentModelsUseCase = GetPaymentModelsUseCase(mockAppRepository)
    }

    @Test
    fun `test execute with valid data should return success`() {
        // Arrange
        val authenticatedUser = getMockedUserModel(id = "1")
        val accounts = listOf(
            mockAccount(id = "1", paymentModel = listOf()),
            mockAccount(id = "2", paymentModel = emptyList())
        )
        coEvery { mockAppRepository.readUser() } returns authenticatedUser
        coEvery { mockAppRepository.getAccounts(authenticatedUser.id) } returns
                Result.success(accounts)

        // Act
        val result = runBlocking { getPaymentModelsUseCase.execute() }

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(accounts.flatMap { it.payments }, result.getOrNull())
    }

    @Test
    fun `test execute with error getting accounts should return failure`() {
        // Arrange
        val authenticatedUser = getMockedUserModel(id = "user123")
        coEvery { mockAppRepository.readUser() } returns authenticatedUser
        coEvery { mockAppRepository.getAccounts(authenticatedUser.id) } returns Result
            .failure(Exception("Could not retrieve account payments"))

        // Act
        val result = runBlocking { getPaymentModelsUseCase.execute() }

        // Assert
        assertTrue(result.isFailure)
        assertEquals(Exception::class, result.exceptionOrNull()!!::class)
    }

    private fun mockAccount(id: String, paymentModel: List<PaymentModel>): AccountDetailsModel {
        return AccountDetailsModel(
            id = id,
            userId = "",
            createdAt = "",
            account = "",
            amount = "",
            payments = paymentModel
        )

    }

    private fun getMockedUserModel(id: String): UserModel {
        return UserModel(
            id = id,
            name = "Miguel",
            avatar = "",
            createdAt = "",
            email = "Crystal_Fisher@hotmail.com",
            password = "A12345"
        )
    }
}
