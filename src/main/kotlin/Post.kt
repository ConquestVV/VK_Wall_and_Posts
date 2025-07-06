package ru.netology

data class Post(
    val id: Int,               // идентификатор записи
    val ownerId: Int,          // идентификатор владельца стены, на который размещена запись
    val text: String?,          // текст записи
    val date: Int,             // время публикации записи
    val fromId: Int,           // идентификатор автора записи
    val replyOwnerId: Int,     // идентификатор владельца записи, в ответ на которую была оставлена текущая
    val replyPostId: Int,      // идентификатор записи, в ответ на которую была оставлена текущая
    val copyright: String,     // источник материала
    val postType: String,      // тип записи (post, copy, reply, postpone, suggest)
    val signerId: Int,         // идентификатор автора, если запись была опубликована от имени сообщества
    val canPin: Boolean,       // может ли текущий юзер закрепить запись
    val canDelete: Boolean,    // может ли текущий юзер удалить запись
    val canEdit: Boolean,      // может ли текущий юзер редактировать запись
    val isPinned: Boolean,     // информация о том, что запись закреплена
    val isFavorite: Boolean,    // информация о том, что запись сохранена в закладки
    val likes: Likes = Likes(0, false, true, true),
    val attachment: List<Attachments>,
) {
    override fun toString(): String {
        return "Post (id = $id, ownerId = $ownerId, text = $message, likes = $likes, attach: $attachment)"
    }

    var message = text ?: "404 not found :("
}