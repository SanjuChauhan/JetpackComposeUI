package com.jio.jetpack.compose.dashboard.models

data class FavoriteModel(
    val favoriteName: String,
    val roomName: String,
    val icon: Int,
    val isEnabled: Boolean = true
)
