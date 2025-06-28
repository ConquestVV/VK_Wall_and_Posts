package ru.netology

fun main() {
    val firstPost = Post(1, 1, "text", 1, 1, 1, 1, "post", "text", 1, true, true, true, true, true)
    val secondPost = Post(2, 2, "text", 2, 2, 2, 2, "post", "text", 2, true, true, true, true, true)

    WallService.add(firstPost)
    WallService.add(secondPost)
    WallService.update(firstPost)

    println(WallService.get(0))
    println(WallService.get(1))
}