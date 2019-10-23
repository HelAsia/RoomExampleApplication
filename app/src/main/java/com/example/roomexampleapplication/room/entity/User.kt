package com.example.roomexampleapplication.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                @ColumnInfo(name = "name") var userName: String,
                @ColumnInfo(name = "surname") var userSurname: String

)