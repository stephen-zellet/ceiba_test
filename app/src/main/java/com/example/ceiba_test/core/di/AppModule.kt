package com.example.ceiba_test.core.di

import android.content.Context
import android.util.Log
import com.example.ceiba_test.core.basedomain.IExceptionHandler
import com.example.ceiba_test.core.basedomain.postService
import com.example.ceiba_test.core.basedomain.userService
import com.example.ceiba_test.feature.post.domain.PostDataSource
import com.example.ceiba_test.feature.post.domain.PostRepository
import com.example.ceiba_test.feature.post.interactors.GetPostByWebService
import com.example.ceiba_test.feature.post.ui.models.PostViewModel
import com.example.ceiba_test.feature.user.interactors.FetchUsersFromWebService
import com.example.ceiba_test.feature.user.interactors.ShowAllDatabaseUsers
import com.example.ceiba_test.feature.user.interactors.ValidateIfRequireFetch
import com.example.ceiba_test.feature.user.repository.UserRepository
import com.example.ceiba_test.feature.user.repository.datasource.local.UserLocalDataSource
import com.example.ceiba_test.feature.user.repository.datasource.remote.UserRemoteDataSource
import com.example.ceiba_test.feature.user.ui.models.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    @ViewModelScoped
    fun userRemoteDatasource(): UserRemoteDataSource = UserRemoteDataSource(userService)

    @Provides
    @ViewModelScoped
    fun postDataSource(): PostDataSource = PostDataSource(postService)

    @Provides
    @ViewModelScoped
    fun userlocalDataSource(@ApplicationContext context: Context) = UserLocalDataSource(context)


    @Provides
    @ViewModelScoped
    fun userRepository(URD: UserRemoteDataSource, ULC: UserLocalDataSource): UserRepository =
        UserRepository(URD, ULC)


    @Provides
    @ViewModelScoped
    fun bindUserViewModel(
        getFecthFromWebService: FetchUsersFromWebService,
        showAllDatabaseUsers: ShowAllDatabaseUsers,
        validateIdRequireFetch: ValidateIfRequireFetch
    ) = UserViewModel(getFecthFromWebService, showAllDatabaseUsers, validateIdRequireFetch)


    @Provides
    @ViewModelScoped
    fun bindPostViewModel(getPostByWebService: GetPostByWebService)
    =PostViewModel(getPostByWebService)


    @Provides
    @ViewModelScoped
    fun provideExceptionHandle() = object : IExceptionHandler {
        override fun handle(t: Throwable) {
            t.message?.let { Log.e("errorred", it) }
        }
    }

    @Provides
    @ViewModelScoped
    fun provideFetchUsersFromWebService(
        userRepository: UserRepository,
        handler: IExceptionHandler
    ) = FetchUsersFromWebService(userRepository, handler)

    @Provides
    @ViewModelScoped
    fun provideshowAllDatabaseUsers(
        userRepository: UserRepository,
        handler: IExceptionHandler
    ) = ShowAllDatabaseUsers(userRepository, handler)

    @Provides
    @ViewModelScoped
    fun validateIdRequireFetch(
        userRepository: UserRepository,
        handler: IExceptionHandler
    ) = ValidateIfRequireFetch(userRepository, handler)


    @Provides
    @ViewModelScoped
    fun getPostByWebService(
        postRepository: PostRepository,
        handler: IExceptionHandler
    ) = GetPostByWebService(postRepository, handler)


    @Provides
    @ViewModelScoped
    fun postRepository(dataSource: PostDataSource) = PostRepository(dataSource)

}