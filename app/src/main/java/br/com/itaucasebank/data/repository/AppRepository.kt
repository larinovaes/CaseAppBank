package br.com.itaucasebank.data.repository

import br.com.itaucasebank.data.infrastructure.ApiExecutor
import br.com.itaucasebank.data.local.dao.UserDao
import br.com.itaucasebank.data.remote.api.AppApi
import br.com.itaucasebank.domain.mapper.toUserEntity
import br.com.itaucasebank.domain.mapper.toUserModel
import br.com.itaucasebank.domain.mapper.toUserModels
import br.com.itaucasebank.domain.model.AccountDetailsModel
import br.com.itaucasebank.domain.model.UserModel
import br.com.itaucasebank.domain.mapper.toModel

class AppRepository(
    private val appApi: AppApi,
    private val userDao: UserDao,
    private val apiExecutor: ApiExecutor,
) {

    suspend fun getUsers(): Result<List<UserModel>> {
        return apiExecutor.executeRequest { appApi.getUsers() }
            .map { it.toUserModels() }
    }

    suspend fun getAccounts(userId: String): Result<List<AccountDetailsModel>> {
        return apiExecutor.executeRequest { appApi.getAccounts(userId) }
            .map { it.toModel() }
    }

    suspend fun readUser(): UserModel {
        // Recupera o primeiro usuário da lista e converte em model.
        // Sempre irá existir apenas um único usuário salvo na DB, sendo
        // referente ao usuário autenticado.
        return userDao.getAllUser().first().toUserModel()
    }

    suspend fun saveUser(userModel: UserModel) {
        userDao.update(listOf(userModel.toUserEntity()))
    }
}
