package com.example.ceiba_test.feature.post.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceiba_test.core.basedomain.Result
import com.example.ceiba_test.feature.post.interactors.GetPostByWebService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostViewModel
@Inject constructor(
    var getPostByWebService: GetPostByWebService
) : ViewModel(){

    private val _result: MutableLiveData<StatusView> = MutableLiveData()
    val result: LiveData<StatusView> = _result


    fun getAllPostByWeb(idUser: Int) {
            viewModelScope.launch(Dispatchers.IO) {

                _result.postValue(
                    StatusView(
                        isLoading = true,
                        showError = false,
                        error = "",
                        listOf()
                    )
                )

                getPostByWebService(idUser).collect { result ->

                    when(result){
                        is Result.Success -> {
                            _result.postValue(
                                StatusView(
                                    isLoading = false,
                                    showError = false,
                                    error = "",
                                    result.result.map { ItemPost(it.userId,it.title,it.body) }
                                )
                            )
                        }

                        is Result.Failure ->{
                            _result.postValue(
                                result.error?.message?.let { it1 ->
                                    StatusView(isLoading = false,
                                        showError = true,
                                        error = it1,
                                        data = listOf()
                                    )
                                }
                            )
                        }
                    }

                }
            }
        }

}