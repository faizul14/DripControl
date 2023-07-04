package com.faezolfp.dripcontrol.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val IS_LOGIN = booleanPreferencesKey("is_login")
    private val ID_USER = intPreferencesKey("is_iduser")

    fun getIsLogin(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }
    }

    suspend fun saveIsLogin(dataLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGIN] = dataLogin
        }
    }

    suspend fun saveIdUser(idUser: Int) {
        dataStore.edit { preferences ->
            preferences[ID_USER] = idUser
        }
    }

    fun getIdUser(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[ID_USER] ?: 0
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
