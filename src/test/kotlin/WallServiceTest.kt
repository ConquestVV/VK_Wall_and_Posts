package ru.netology
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
class WallServiceTest {

 @Test
 fun add_shouldAssignIdAndAddPost() {
  val post = Post(
   id = 0,
   ownerId = 1,
   text = "Hello",
   date = 123,
   fromId = 1,
   replyOwnerId = 0,
   replyPostId = 0,
   copyright = "",
   postType = "post",
   signerId = 0,
   canPin = true,
   canDelete = true,
   canEdit = true,
   isPinned = false,
   isFavorite = false,
   likes = Likes(0, false, true, true),
   attachment = emptyList(),
   comments = mutableListOf()
  )

  val result = WallService.add(post)

  assertNotEquals(0, result.id) // Убедимся, что id был присвоен автоматически
  assertEquals(post.text, result.text)
 }

 @Test
 fun update_shouldReturnTrue_whenPostExists() {
  val arrayOfAttachments = emptyList<Attachments>()
  val myComment = Comments(1,1,"comment")
  val comments: MutableList<Comments> = mutableListOf(myComment)
  val post = WallService.add(
   Post(0, 1, "Text", 0, 1, 0, 0, "", "post", 0, true, true, true, false, false, likes = Likes(0,true,true,true),arrayOfAttachments, comments)
  )

  val updated = post.copy(text = "Updated!")

  val result = WallService.update(updated)

  assertTrue(result)
 }

 @Test
 fun update_shouldReturnFalse_whenPostDoesNotExist() {
  val arrayOfAttachments = emptyList<Attachments>()
  val myComment = Comments(1,1,"comment")
  val comments: MutableList<Comments> = mutableListOf(myComment)
  val nonExistentPost = Post(0, 1, "Text", 0, 1, 0, 0, "", "post", 0, true, true, true, false, false, likes = Likes(0,true,true,true),arrayOfAttachments,comments)

  val result = WallService.update(nonExistentPost)

  assertFalse(result)
 }

 @Test
 fun shouldThrow() {
  var arrayOfAttachments = emptyList<Attachments>()
  val myComment = Comments(1,1,"comment")
  val comments: MutableList<Comments> = mutableListOf(myComment)
  arrayOfAttachments += Photo("photo",2,1, 1, "text", "text", 1, "text",1, "text")
  val post = Post(1, 1, "ext",1, 1, 1, 1, "text", "text", 1, true, true, true, true, true, Likes(1,true,true,true), arrayOfAttachments, comments)
  WallService.set(0, post)
  WallService.createComment(myComment)
 }

 @Test
 fun createComment_isWorkingCorrectly() {
  var arrayOfAttachments = emptyList<Attachments>()
  val myComment = Comments(1,1,"comment")
  val comments: MutableList<Comments> = mutableListOf(myComment)
  arrayOfAttachments += Photo("photo",2,1, 1, "text", "text", 1, "text",1, "text")
  val post = Post(1, 1, "ext",1, 1, 1, 1, "text", "text", 1, true, true, true, true, true, Likes(1,true,true,true), arrayOfAttachments, comments)
  WallService.set(0, post)
  val result = WallService.createComment(myComment)

  assertEquals(comments.last(), result)
 }
}