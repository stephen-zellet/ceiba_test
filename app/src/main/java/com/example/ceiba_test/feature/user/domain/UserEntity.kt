package com.example.ceiba_test.feature.user.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id:Int=0,
    @ColumnInfo(name = "id_web") var id_web: Int,
    @ColumnInfo(name = "name")   var name: String,
    @ColumnInfo(name = "phone")  var phoneNumber: String,
    @ColumnInfo(name = "email")  var email: String,
)
