package ru.netology

fun main() {
    var arrayOfAttachments = emptyList<Attachments>()
    val comments = mutableListOf<Comments>()

    arrayOfAttachments += Photo("photo", 1, 1,1,"","",1,"",1,"")
    arrayOfAttachments += Audio("audio", 1, 1,1,1,"",1,"")
    arrayOfAttachments += Video("video", 1, 1,1,1,"",1,"")
    arrayOfAttachments += Document("document", 1, 1,1,1,"",1,"")

    val myComment = Comments(1,1,"haha")
    val mySecondComment = Comments(2,2,"omg")

    val firstPost = Post(1, 1, "text", 1, 1, 1, 1, "post", "text", 1, true, true, true, true, true, likes = Likes(0,true,true,true), arrayOfAttachments, comments)
    val secondPost = Post(2, 2, "text", 2, 2, 2, 2, "post", "text", 2, true, true, true, true, true, likes = Likes(0,true,true,true),arrayOfAttachments, comments)

    val firstNote = Note("1","","1",1,1,1,"1",1,"1",1,"1",false)
    val secondNote = Note("1","","1",1,1,1,"1",1,"1",1,"1",false)

    WallService.add(firstPost)
    WallService.add(secondPost)
    WallService.update(firstPost)
    WallService.createComment(myComment)
    WallService.createComment(mySecondComment)

    println(WallService.get(0))
    println(WallService.get(1))
    println(arrayOfAttachments)

    val noteService = NotesService()

    val groceryNote = noteService.add(
        Note(
            ownerId = 100,
            title = "Groceries",
            text = "Milk and bread",
            type = "1",
            id = 1,
            userId = 1,
            artist = "1",
            duration = 1,
            description = "1",
            size = 1,
            ext = "1",
            isDeleted = false,
        )
    )
    println("Added note: $groceryNote")

    noteService.createComment(
        noteId = groceryNote.id,
        ownerId = 100,
        message = "Don't forget eggs",
    )

    noteService.edit(
        groceryNote.copy(
            title = "Groceries (updated)",
            text = "Milk, bread, and eggs",
        )
    )

    println("Comments: ${noteService.getComments(groceryNote.id)}")
    println("Updated note: ${noteService.get(groceryNote.id)}")


}