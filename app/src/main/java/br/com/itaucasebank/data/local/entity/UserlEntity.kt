package br.com.itaucasebank.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.itaucasebank.data.remote.dto.UserDto

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "createdAt")
    val createdAt: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String
) {

    constructor(userDto: UserDto) : this(
        id = userDto.id,
        name = userDto.name,
        avatar = userDto.avatar,
        createdAt = userDto.createdAt,
        email = userDto.email,
        password = userDto.password,
    )
}
