package com.faezolfp.dripcontrol.core.utils

import com.faezolfp.dripcontrol.core.data.local.UserEntity
import com.faezolfp.dripcontrol.core.domain.model.Users

object DataMapper {
    fun dataMapFromModelToEntity(users: Users): UserEntity {
        val data = UserEntity(
            id = users.id,
            username = users.username,
            fulname = users.fullname,
            email = users.email,
            password = users.pasword
        )

        return data
    }
    fun dataMapFromEntityToModel(users: UserEntity): Users {
        val data = Users(
            id = users.id ?: 0,
            username = users.username ?: null,
            fullname = users.fulname ?: null,
            email = users.email ?: null,
            pasword = users.password ?: null
        )

        return data
    }
}