package br.com.itaucasebank.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.itaucasebank.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<UserEntity>

    @Insert
    suspend fun insert(animals: List<UserEntity>)

    @Query("DELETE from user")
    suspend fun deleteAll()

    suspend fun update(animals: List<UserEntity>) {
        deleteAll()
        insert(animals)
    }
}
