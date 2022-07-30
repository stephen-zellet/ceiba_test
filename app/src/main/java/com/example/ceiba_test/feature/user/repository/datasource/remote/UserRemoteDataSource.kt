package com.example.ceiba_test.feature.user.repository.datasource.remote

import com.example.ceiba_test.feature.user.domain.User
import com.example.ceiba_test.feature.user.framework.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    var userService: UserService
) {
    suspend fun getAll(): List<User> {
        return userService.getAllUsers()
    }
}