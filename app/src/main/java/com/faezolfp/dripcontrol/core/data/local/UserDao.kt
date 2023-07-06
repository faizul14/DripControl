package com.faezolfp.dripcontrol.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun userRegister(userEntity: UserEntity)

    @Update
    fun userUpdate(userEntity: UserEntity)

    @Query("SELECT username from UserEntity")
    fun getUsername(): LiveData<String>

    @Query("SELECT id FROM UserEntity WHERE email = :email AND password = :password")
    fun login(email: String, password: String): LiveData<Int>

    @Query("SELECT username FROM UserEntity WHERE id = :idUser")
    fun getUsernameById(idUser: Int): LiveData<String>

    @Query("SELECT * FROM UserEntity WHERE id = :idUser")
    fun getUserById(idUser: Int): LiveData<UserEntity>

}