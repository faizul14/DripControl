package com.faezolfp.dripcontrol.core.utils

import com.faezolfp.dripcontrol.core.data.local.PasienEntity
import com.faezolfp.dripcontrol.core.data.local.UserEntity
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
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
    fun dataMapFromModelToPasienEntity(pasiens: Pasiens): PasienEntity {
        val data = PasienEntity(
            id = pasiens.id,
            nama = pasiens.nama,
            umur = pasiens.umur,
            brtbadan = pasiens.brtbadan,
            banyakcairaninfus = pasiens.banyakcairaninfus,
            lamapemberianinfus = pasiens.lamapemberianinfus,
            tetsanpermenit = pasiens.tetsanpermenit,
            kamar = pasiens.kamar
        )

        return data
    }

    fun dataMapFromListPasienEntityToListPasienModel(input: List<PasienEntity>): List<Pasiens>{
        val dataList = ArrayList<Pasiens>()
        input.map {pasiens ->
            val data = Pasiens(
                id = pasiens.id,
                nama = pasiens.nama,
                umur = pasiens.umur,
                brtbadan = pasiens.brtbadan,
                banyakcairaninfus = pasiens.banyakcairaninfus,
                lamapemberianinfus = pasiens.lamapemberianinfus,
                tetsanpermenit = pasiens.tetsanpermenit,
                kamar = pasiens.kamar
            )
            dataList.add(data)
        }
        return dataList
    }

}