package com.example.ceiba_test.core.basedomain

import android.util.Log
import com.example.ceiba_test.BuildConfig
import com.example.ceiba_test.feature.post.framework.PostService
import com.example.ceiba_test.feature.user.framework.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient{


    fun getUserService():UserService{
       return provideRetrofit(UserService.BASE_URL).create(UserService::class.java)
    }

    fun getPostService(): PostService {
        return provideRetrofit(PostService.BASE_URL).create(PostService::class.java)
    }



    private fun provideRetrofit(url:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor {
                Log.i("API", it)
            }

            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }


}

var userService:UserService = ApiClient.getUserService()
var postService:PostService = ApiClient.getPostService()