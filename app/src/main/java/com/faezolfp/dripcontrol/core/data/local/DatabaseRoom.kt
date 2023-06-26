package com.faezolfp.dripcontrol.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: DatabaseRoom? = null
        @JvmStatic
        fun getDatabase(context: Context): DatabaseRoom {
            if (INSTANCE == null) {
                synchronized(DatabaseRoom::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DatabaseRoom::class.java, "user_database")
                        .build()
                }
            }
            return INSTANCE as DatabaseRoom
        }
    }
}