package ru.netology

data class Comments(
    val count: Int,
    val postId: Int,// количество комментариев
    val comment: String
) {


    override fun toString(): String {
        return "$comment. Added to post $postId."
    }
}
