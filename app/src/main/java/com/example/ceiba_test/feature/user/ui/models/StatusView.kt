package com.example.ceiba_test.feature.user.ui.models

data class StatusView(
    var isLoading:Boolean=false,
    var showError:Boolean=false,
    var error:String="",
    var data:List<ItemUser> = listOf()
)
