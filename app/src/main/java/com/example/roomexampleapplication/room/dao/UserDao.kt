package com.example.roomexampleapplication.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.roomexampleapplication.room.entity.User
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun insertUser(user: User): Single<Long>

    @Delete
    fun deleteUser(user: User) : Completable

    @Update
    fun updateUser(user: User) : Completable

    @Query("SELECT * FROM user")
    fun getUser(): Maybe<User>
}