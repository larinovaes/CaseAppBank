package br.com.itaucasebank.domain.mapper

import br.com.itaucasebank.data.local.entity.UserEntity
import br.com.itaucasebank.data.remote.dto.UserDto
import br.com.itaucasebank.domain.model.UserModel

fun List<UserDto>.toUserModels(): List<UserModel> {
    return this.map { it.toUserModel() }
}

fun UserDto.toUserModel() = UserModel(
    id = this.id,
    name = this.name,
    avatar = this.avatar,
    email = this.email,
    password = this.password,
    createdAt = this.createdAt,
)

fun UserEntity.toUserModel() = UserModel(
    id = this.id,
    name = this.name,
    avatar = this.avatar,
    email = this.email,
    password = this.password,
    createdAt = this.createdAt,
)

fun UserModel.toUserEntity() = UserEntity(
    id = this.id,
    name = this.name,
    avatar = this.avatar,
    email = this.email,
    password = this.password,
    createdAt = this.createdAt,
)
