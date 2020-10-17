package com.example.evaluationtestandroid.models

data class AlbumModel (
    val id: Int,
    val name: String = "",
    val author: String = "",
    val description: String = "",
    val imageUrl: String = "empty",
    val songsCount: Int
)