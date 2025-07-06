package ru.netology

fun main() {
    var arrayOfAttachments = emptyList<Attachments>()

    arrayOfAttachments += Photo("photo", 1, 1,1,"","",1,"",1,"")
    arrayOfAttachments += Audio("audio", 1, 1,1,1,"",1,"")
    arrayOfAttachments += Note("note", 1, 1,1,"",1,"",1,"")
    arrayOfAttachments += Video("video", 1, 1,1,1,"",1,"")
    arrayOfAttachments += Document("document", 1, 1,1,1,"",1,"")

    val firstPost = Post(1, 1, "text", 1, 1, 1, 1, "post", "text", 1, true, true, true, true, true, likes = Likes(0,true,true,true),arrayOfAttachments)
    val secondPost = Post(2, 2, "text", 2, 2, 2, 2, "post", "text", 2, true, true, true, true, true, likes = Likes(0,true,true,true),arrayOfAttachments)

    WallService.add(firstPost)
    WallService.add(secondPost)
    WallService.update(firstPost)

    println(WallService.get(0))
    println(WallService.get(1))
    println(arrayOfAttachments)
}