package com.example.ceiba_test.feature.user.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ceiba_test.feature.user.domain.UserEntity

@Database(
    entities = [UserEntity::class,], version = 1
)
 abstract class AppDatabase:RoomDatabase(){
     abstract fun userDao():UserDao
}