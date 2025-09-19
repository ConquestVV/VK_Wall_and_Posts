package ru.netology

fun main() {
    var arrayOfAttachments = emptyList<Attachments>()
    val comments = mutableListOf<Comments>()

    arrayOfAttachments += Photo("photo", 1, 1,1,"","",1,"",1,"")
    arrayOfAttachments += Audio("audio", 1, 1,1,1,"",1,"")
    arrayOfAttachments += Note("note", 1, 1,1,"",1,"",1,"")
    arrayOfAttachments += Video("video", 1, 1,1,1,"",1,"")
    arrayOfAttachments += Document("document", 1, 1,1,1,"",1,"")

    val myComment = Comments(1,1,"haha")
    val mySecondComment = Comments(2,2,"omg")

    val firstPost = Post(1, 1, "text", 1, 1, 1, 1, "post", "text", 1, true, true, true, true, true, likes = Likes(0,true,true,true), arrayOfAttachments, comments)
    val secondPost = Post(2, 2, "text", 2, 2, 2, 2, "post", "text", 2, true, true, true, true, true, likes = Likes(0,true,true,true),arrayOfAttachments, comments)

    WallService.add(firstPost)
    WallService.add(secondPost)
    WallService.update(firstPost)
    WallService.createComment(myComment)
    WallService.createComment(mySecondComment)

    println(WallService.get(0))
    println(WallService.get(1))
    println(arrayOfAttachments)
}