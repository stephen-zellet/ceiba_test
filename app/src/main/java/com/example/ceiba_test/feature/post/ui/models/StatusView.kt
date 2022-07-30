package com.example.ceiba_test.feature.post.ui.models

data class StatusView(
    var isLoading: Boolean,
    var showError: Boolean,
    var error: String,
    var data: List<ItemPost>
)