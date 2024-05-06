package br.com.itaucasebank.domain

import br.com.itaucasebank.data.repository.AppRepository
import br.com.itaucasebank.domain.model.AccountDetailsModel
import br.com.itaucasebank.domain.model.UserAndAccountDetailsModel
import br.com.itaucasebank.domain.model.UserModel
import br.com.itaucasebank.domain.usecase.GetUserAndAccountDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserAndAccountDetailsUseCaseTest {

    private val mockAppRepository = mockk<AppRepository>()

    private lateinit var getUserAndAccountDetailsUseCase: GetUserAndAccountDetailsUseCase

    @Before
    fun setUp() {
        getUserAndAccountDetailsUseCase = GetUserAndAccountDetailsUseCase(mockAppRepository)
    }

    @Test
    fun `test execute with valid data should return success`() {
        // Arrange
        val userModel = getMockedUserModel()
        val account = mockAccount()
        coEvery { mockAppRepository.readUser() } returns userModel
        coEvery { mockAppRepository.getAccounts(userModel.id) } returns
                Result.success(listOf(account))

        // Act
        val result = runBlocking { getUserAndAccountDetailsUseCase.execute() }

        // Assert
        val expectedModel = UserAndAccountDetailsModel(
            userName = userModel.name,
            profileImageUrl = userModel.avatar,
            accountNumber = account.account,
            agencyNumber = "342",
            balanceValue = account.amount
        )
        assertTrue(result.isSuccess)
        assertEquals(expectedModel, result.getOrNull())
    }

    @Test
    fun `test execute with null user should return failure`() {
        // Arrange
        val userModel = getMockedUserModel()
        coEvery { mockAppRepository.readUser() } returns userModel
        coEvery { mockAppRepository.getAccounts(any()) } returns Result.failure(Exception())

        // Act
        val result = runBlocking { getUserAndAccountDetailsUseCase.execute() }

        // Assert
        assertTrue(result.isFailure)
        assertEquals(Exception::class, result.exceptionOrNull()!!::class)
        assertEquals("Could not retrieve user account details", result.exceptionOrNull()!!.message)
    }

    private fun mockAccount(): AccountDetailsModel {
        return AccountDetailsModel(
            id = "1",
            userId = "",
            createdAt = "",
            account = "12345",
            amount = "1000.0",
            payments = listOf()
        )

    }

    private fun getMockedUserModel(): UserModel {
        return UserModel(
            id = "1",
            name = "Miguel",
            avatar = "url",
            createdAt = "",
            email = "Crystal_Fisher@hotmail.com",
            password = "A12345"
        )
    }
}