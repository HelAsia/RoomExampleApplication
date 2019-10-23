package com.example.roomexampleapplication.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomexampleapplication.room.dao.UserDao
import com.example.roomexampleapplication.room.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private val DATABASE_NAME = "userdb.db"
        private var INSTANCE: UserDatabase? = null

        fun getUserDatabase(context: Context): UserDatabase {
            val temporaryInstance = INSTANCE
            if(temporaryInstance != null){
                return temporaryInstance
            }

            synchronized(UserDatabase::class) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    UserDatabase::class.java, DATABASE_NAME)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}