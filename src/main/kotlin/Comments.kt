package ru.netology

data class Comments(
    val count: Int,             // количество комментариев
    val canPost: Boolean,       // может ли текущий юзер комментировать запись
    val groupsCanPost: Boolean, // могут ли сообщества комментировать запись
    val canClose: Boolean,      // может ли текущий юзер закрыть комментарии к записи
    val canOpen: Boolean,       // может ли текущий юзер открыть комментарии к записи
)
