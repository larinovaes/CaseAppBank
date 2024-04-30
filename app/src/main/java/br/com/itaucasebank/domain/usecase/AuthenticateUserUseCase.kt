package br.com.itaucasebank.domain.usecase

import android.util.Patterns
import br.com.itaucasebank.data.repository.AppRepository
import br.com.itaucasebank.domain.exception.InvalidEmailOrPasswordException

class AuthenticateUserUseCase(private val appRepository: AppRepository) {

    suspend fun execute(email: String, password: String): Result<Unit> {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(InvalidEmailOrPasswordException())
        }
        if (!isPasswordValid(password)){
            return Result.failure(InvalidEmailOrPasswordException())
        }
        return authenticateUser(email, password)
    }

    /**
     * Método que simula a autenticação do usuário utilizando apenas o email como referência.
     *
     * Este método simula uma autenticação verificando se o email fornecido corresponde a algum dos
     * usuários obtidos da API. A senha não é utilizada neste processo, pois a senha
     * retornada pela API não é válida pela validação local.
     *
     * @param email O endereço de email fornecido para autenticação.
     * @param password A senha fornecida para autenticação (não é utilizada neste método).
     * @return Um [Result] indicando se a autenticação foi bem-sucedida ou não.
     * @throws InvalidEmailOrPasswordException Se o email fornecido não corresponder a nenhum usuário.
     */
    private suspend fun authenticateUser(email: String, password: String): Result<Unit> {
        val users = appRepository.getUsers().getOrNull()
            ?: return Result.failure(Exception("Could not retrieve list of users"))
        val authenticatedUser = users.find { it.email == email }
            ?: return Result.failure(InvalidEmailOrPasswordException())
        appRepository.saveUser(authenticatedUser)
        return Result.success(Unit)
    }

    private fun isPasswordValid(password: String): Boolean {
        // Verifica se a senha contém pelo menos uma letra maiúscula e cinco números
        val uppercaseRegex = Regex("[A-Z]")
        val digitRegex = Regex("\\d{5,}")

        return uppercaseRegex.containsMatchIn(password) && digitRegex.containsMatchIn(password)
    }
}
