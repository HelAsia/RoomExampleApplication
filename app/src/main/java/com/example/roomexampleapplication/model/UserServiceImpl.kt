package com.example.roomexampleapplication.model

import com.example.roomexampleapplication.room.dao.UserDao
import com.example.roomexampleapplication.room.entity.User
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserServiceImpl(private val userDao: UserDao): UserService {
    override fun insertUser(user: User) : Single<Long> {
        return userDao.insertUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteUser(user: User) : Completable {
        return Completable.fromAction { userDao.deleteUser(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateUser(user: User) : Completable {
        return Completable.fromAction { userDao.updateUser(user)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUser(): Maybe<User> {
        return userDao.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}