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

    fun set(index: Int, post: Post){
        posts += post
        posts[index] = post
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

    fun createComment(comment: Comments): Comments {
        val id = comment.postId
        val post = findById(id) ?: throw PostNotFoundException("No post with id $id")
        post.comments += comment
        return  post.comments.last()
    }

    fun findById(id: Int): Post? {
        for (post in posts) {
            if (post.id == id) {
                return post
            }
        }
        return null
    }
}