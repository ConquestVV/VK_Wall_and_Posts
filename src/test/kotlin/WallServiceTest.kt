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
   likes = Likes(0, false, true, true)
  )

  val result = WallService.add(post)

  assertNotEquals(0, result.id) // Убедимся, что id был присвоен автоматически
  assertEquals(post.text, result.text)
 }

 @Test
 fun update_shouldReturnTrue_whenPostExists() {
  val post = WallService.add(
   Post(0, 1, "Text", 0, 1, 0, 0, "", "post", 0, true, true, true, false, false)
  )

  val updated = post.copy(text = "Updated!")

  val result = WallService.update(updated)

  assertTrue(result)
 }

 @Test
 fun update_shouldReturnFalse_whenPostDoesNotExist() {
  val nonExistentPost = Post(999, 1, "Missing", 0, 1, 0, 0, "", "post", 0, true, true, true, false, false)

  val result = WallService.update(nonExistentPost)

  assertFalse(result)
 }
}