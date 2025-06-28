package ru.netology

data class Reposts(
    val count: Int,                // количество репостов
    val userRepost: Boolean,       // наличие репоста от текущего юзера
)
