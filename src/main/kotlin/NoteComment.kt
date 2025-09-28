package ru.netology

data class NoteComment(
    val id: Int = 0,
    val noteId: Int,
    val ownerId: Int,
    var message: String,
    var isDeleted: Boolean = false,
)