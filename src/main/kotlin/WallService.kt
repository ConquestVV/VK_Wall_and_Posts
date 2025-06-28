package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 1

    fun get(index: Int) = posts.getOrNull(index)

    fun add(post: Post): Post {
        var postCopy = post.copy(id = nextId++)
        posts += postCopy
        return postCopy
    }

    fun update(post: Post): Boolean {

        for ((index, existingPost) in posts.withIndex()) {
            if (existingPost.id == post.id) {
                posts[index] = post.copy(
                    ownerId = post.ownerId + 1,
                    fromId = post.fromId + 1,
                )
                return true
            }
        }
        return false
    }
}