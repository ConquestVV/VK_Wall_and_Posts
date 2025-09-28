package ru.netology.notes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.netology.Note
import ru.netology.NotesService
import ru.netology.NoteCommentNotFoundException
import ru.netology.NoteNotFoundException


class NotesServiceTests {

    @Test
    fun `add should assign identifier`() {
        val service = NotesService()

        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 0,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )

        assertNotEquals(0, note.id)
        val stored = service.get(note.id)
        assertNotNull(stored)
        assertEquals(note.id, stored!!.id)
        assertEquals(note.title, stored.title)
        assertEquals(note.text, stored.text)
    }

    @Test
    fun `edit should update existing note`() {
        val service = NotesService()
        val original = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )

        val result = service.edit(original.copy(title = "New title", text = "New text"))

        assertTrue(result)
        assertEquals("New title", service.get(original.id)?.title)
    }

    @Test
    fun `edit should return false when note does not exist`() {
        val service = NotesService()
        val result = service.edit(
            Note(
                id = 999,
                ownerId = 1,
                title = "",
                text = "",
                type = "type",
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )

        assertFalse(result)
    }


    @Test
    fun `createComment should attach comment to note`() {
        val service = NotesService()
        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )

        val comment = service.createComment(noteId = note.id, ownerId = 2, message = "Great note")

        assertNotEquals(0, comment.id)
        assertEquals(listOf(comment), service.getComments(note.id))
    }

    @Test
    fun `createComment should throw when note not found`() {
        val service = NotesService()

        val exception = assertThrows(NoteNotFoundException::class.java) {
            service.createComment(noteId = 1, ownerId = 1, message = "Missing")
        }

        assertEquals("Note with id=1 not found", exception.message)
    }


    @Test
    fun `deleteComment should throw when comment missing`() {
        val service = NotesService()

        assertThrows(NoteCommentNotFoundException::class.java) {
            service.deleteComment(1)
        }
    }

    @Test
    fun `editComment should throw when comment missing`() {
        val service = NotesService()

        assertThrows(NoteCommentNotFoundException::class.java) {
            service.editComment(1, "Updated")
        }
    }

    @Test
    fun `deleteComment should mark comment as deleted`() {
        val service = NotesService()
        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )
        val comment = service.createComment(noteId = note.id, ownerId = 2, message = "Great note")

        val result = service.deleteComment(comment.id)

        assertTrue(result)
        assertTrue(service.getComments(note.id, includeDeleted = true).first().isDeleted)
        assertTrue(service.getComments(note.id).isEmpty())
    }

    @Test
    fun `deleteComment should return false when comment already deleted`() {
        val service = NotesService()
        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )
        val comment = service.createComment(noteId = note.id, ownerId = 2, message = "Great note")
        service.deleteComment(comment.id)

        val result = service.deleteComment(comment.id)

        assertFalse(result)
    }

    @Test
    fun `editComment should update text`() {
        val service = NotesService()
        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )
        val comment = service.createComment(noteId = note.id, ownerId = 2, message = "Great note")

        val result = service.editComment(comment.id, "Updated")

        assertTrue(result)
        assertEquals("Updated", service.getComments(note.id).first().message)
    }

    @Test
    fun `editComment should return false when comment deleted`() {
        val service = NotesService()
        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )
        val comment = service.createComment(noteId = note.id, ownerId = 2, message = "Great note")
        service.deleteComment(comment.id)

        val result = service.editComment(comment.id, "Updated")

        assertFalse(result)
    }

    @Test
    fun `getComments should throw for missing note`() {
        val service = NotesService()

        val exception = assertThrows(NoteNotFoundException::class.java) {
            service.getComments(noteId = 1)
        }

        assertEquals("Note with id=1 not found", exception.message)
    }

    @Test
    fun `restoreComment should reactivate comment`() {
        val service = NotesService()
        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )
        val comment = service.createComment(noteId = note.id, ownerId = 2, message = "Great note")
        service.deleteComment(comment.id)

        val result = service.restoreComment(comment.id)

        assertTrue(result)
        assertFalse(service.getComments(note.id).first().isDeleted)
    }

    @Test
    fun `restoreComment should throw when comment missing`() {
        val service = NotesService()

        assertThrows(NoteCommentNotFoundException::class.java) {
            service.restoreComment(1)
        }
    }

    @Test
    fun `restoreComment should throw when note deleted`() {
        val service = NotesService()
        val note = service.add(
            Note(
                ownerId = 1,
                title = "Title",
                text = "Text",
                type = "type",
                id = 1,
                userId = 1,
                duration = 1,
                description = "text",
                artist = "artist",
                size = 1,
                ext = "ext"
            )
        )
        val comment = service.createComment(noteId = note.id, ownerId = 2, message = "Great note")
        service.deleteComment(comment.id)
        service.delete(note.id)

        val exception = assertThrows(NoteNotFoundException::class.java) {
            service.restoreComment(comment.id)
        }

        assertEquals("Note with id=${note.id} not found or deleted", exception.message)
    }
}