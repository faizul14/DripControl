package com.faezolfp.dripcontrol.core.utils

import com.faezolfp.dripcontrol.core.data.local.UserEntity
import com.faezolfp.dripcontrol.core.domain.model.Users

object DataMapper {
    fun dataMapFromModelToEntity(users: Users): UserEntity {
        val data = UserEntity(
            username = users.username,
            fulname = users.fullname,
            email = users.email,
            password = users.pasword
        )

        return data
    }
}