package com.example.roomexampleapplication.model

import com.example.roomexampleapplication.room.entity.User
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserService {
    fun insertUser(user: User) : Single<Long>
    fun deleteUser(user: User) : Completable
    fun updateUser(user: User) : Completable
    fun getUser(): Maybe<User>
}