package com.example.roomexampleapplication

import android.app.Activity
import android.app.Application
import com.example.roomexampleapplication.model.UserServiceImpl
import com.example.roomexampleapplication.room.database.UserDatabase

class MyApplication: Application() {
    private lateinit var userServiceImpl: UserServiceImpl

    companion object {
        fun get(activity: Activity): MyApplication{
            return activity.application as MyApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

       // this.deleteDatabase("userdb.db")
        val userDatabase = UserDatabase.getUserDatabase(this)
        userServiceImpl = UserServiceImpl(userDatabase.userDao())


    }

    fun getUserService(): UserServiceImpl{
        return userServiceImpl
    }
}