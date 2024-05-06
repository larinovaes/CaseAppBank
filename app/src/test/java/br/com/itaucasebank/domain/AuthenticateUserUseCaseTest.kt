package br.com.itaucasebank.domain

import br.com.itaucasebank.data.repository.AppRepository
import br.com.itaucasebank.domain.exception.InvalidEmailOrPasswordException
import br.com.itaucasebank.domain.model.UserModel
import br.com.itaucasebank.domain.usecase.AuthenticateUserUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AuthenticateUserUseCaseTest {

    private val mockAppRepository = mockk<AppRepository>()

    private lateinit var authenticateUserUseCase: AuthenticateUserUseCase

    @Before
    fun setUp() {
        authenticateUserUseCase = AuthenticateUserUseCase(mockAppRepository)
    }

    @Test
    fun `test execute with valid email and password should return success`() {
        // Arrange
        val email = "Crystal_Fisher@hotmail.com"
        val password = "A12345"
        val mockedUserModel = getMockedUserModel().copy(email = email)
        coEvery { mockAppRepository.getUsers() } returns Result.success(listOf(mockedUserModel))
        coEvery { mockAppRepository.saveUser(any()) } returns Unit

        // Act
        val result = runBlocking { authenticateUserUseCase.execute(email, password) }

        // Assert
        TestCase.assertTrue(result.isSuccess)
        TestCase.assertEquals(Unit, result.getOrNull())
    }

    @Test
    fun `test execute with invalid email should return failure`() {
        // Arrange
        val invalidEmail = "invalid-email"
        val password = "Password123"

        // Act
        val result = runBlocking { authenticateUserUseCase.execute(invalidEmail, password) }

        // Assert
        TestCase.assertTrue(result.isFailure)
        TestCase.assertEquals(
            InvalidEmailOrPasswordException::class,
            result.exceptionOrNull()!!::class
        )
    }

    @Test
    fun `test execute with invalid password should return failure`() {
        // Arrange
        val email = "test@example.com"
        val invalidPassword = "password" // invalid password

        // Act
        val result = runBlocking { authenticateUserUseCase.execute(email, invalidPassword) }

        // Assert
        TestCase.assertTrue(result.isFailure)
        TestCase.assertEquals(
            InvalidEmailOrPasswordException::class,
            result.exceptionOrNull()!!::class
        )
    }

    private fun getMockedUserModel(): UserModel {
        return UserModel(
            id = "1",
            name = "Miguel",
            avatar = "",
            createdAt = "",
            email = "test@email.com",
            password = "A12345"
        )
    }
}
