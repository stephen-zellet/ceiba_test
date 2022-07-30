package com.example.ceiba_test.feature.user.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceiba_test.core.basedomain.Result
import com.example.ceiba_test.feature.user.interactors.FetchUsersFromWebService
import com.example.ceiba_test.feature.user.interactors.ShowAllDatabaseUsers
import com.example.ceiba_test.feature.user.interactors.ValidateIfRequireFetch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.zip.ZipEntry
import javax.annotation.meta.When
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(
    var fecthFromWebService: FetchUsersFromWebService,
    var showAllDatabaseUsers: ShowAllDatabaseUsers,
    var validateIdRequireFetch: ValidateIfRequireFetch
) : ViewModel() {


    private val _result: MutableLiveData<StatusView> = MutableLiveData()
    val result: LiveData<StatusView> = _result


    @OptIn(ExperimentalCoroutinesApi::class)
    fun fecthDataFromServer() {

        viewModelScope.launch(Dispatchers.IO) {

            validateIdRequireFetch().collect { result ->

                when (result) {
                    is Result.Success -> {
                        if (result.result) {
                            _result.postValue(StatusView(true, false, "", listOf()))
                            fecthFromWebService().collect {

                                when (it) {
                                    is Result.Success -> {
                                        _result.postValue(StatusView(false, false, "", listOf()))
                                        showDatafromDb()
                                    }
                                    is Result.Failure -> {
                                        _result.postValue(it.error?.message?.let { it1 ->
                                            StatusView(
                                                false, true,
                                                it1, listOf()
                                            )
                                        })


                                    }
                                }


                            }
                        } else {
                            showDatafromDb()
                        }

                    }
                    is Result.Failure -> {
                        _result.postValue(result.error?.message?.let { it1 ->
                            StatusView(
                                false, true,
                                it1, listOf()
                            )
                        })
                    }
                }
            }

        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun showDatafromDb() {
        viewModelScope.launch(Dispatchers.IO) {

            showAllDatabaseUsers().collect {

                when (it) {
                    is Result.Success -> {

                        _result.postValue(StatusView(false, false, "", it.result.map {
                            ItemUser(
                                id = it.id_web,
                                name = it.name,
                                phoneNumber = it.phoneNumber,
                                email = it.email
                            )
                        }))


                    }
                    is Result.Failure -> {
                        _result.postValue(it.error?.message?.let { it1 ->
                            StatusView(
                                false, true,
                                it1, listOf()
                            )
                        })
                    }
                }

            }
        }
    }


}