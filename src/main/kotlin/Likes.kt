package ru.netology

data class Likes(
    val count: Int,                 // число пользователей, которым понравилась запись
    val userLikes: Boolean,         // наличие отметки "Мне нравится" от текущего юзера
    val canLike: Boolean,           // может ли текущий юзер поставить отметку "Мне нравится"
    val canPublish: Boolean         // может ли текущий юзер сделать репост этой записи
)