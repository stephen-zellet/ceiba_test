package com.example.ceiba_test.feature.user.framework

import com.example.ceiba_test.feature.user.domain.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    companion object{
        const val BASE_URL="https://jsonplaceholder.typicode.com/"
        const val ENDPOINT ="users"
    }

    @GET(ENDPOINT)
    suspend fun getAllUsers():List<User>
}