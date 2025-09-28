package ru.netology

class NotesService : CrudService<Note> {
    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<NoteComment>()
    private var nextNoteId = 1
    private var nextCommentId = 1

    override fun add(entity: Note): Note {
        val note = entity.copy(
            id = nextNoteId++,
            isDeleted = false,
        )
        notes += note
        return note.copy()
    }

    override fun delete(id: Int): Boolean {
        val note = notes.firstOrNull { it.id == id && !it.isDeleted } ?: return false
        note.isDeleted = true
        comments.filter { it.noteId == id }.forEach { it.isDeleted = true }
        return true
    }

    override fun edit(entity: Note): Boolean {
        val note = notes.firstOrNull { it.id == entity.id && !it.isDeleted } ?: return false
        note.title = entity.title
        note.text = entity.text
        return true
    }

    override fun get(id: Int): Note? =
        notes.firstOrNull { it.id == id && !it.isDeleted }?.copy()



    fun getById(id: Int, includeDeleted: Boolean = false): Note? =
        notes.firstOrNull { it.id == id && (includeDeleted || !it.isDeleted) }?.copy()

    fun createComment(noteId: Int, ownerId: Int, message: String): NoteComment {
        requireNote(noteId)
        val comment = NoteComment(
            id = nextCommentId++,
            noteId = noteId,
            ownerId = ownerId,
            message = message,
        )
        comments += comment
        return comment.copy()
    }

    fun deleteComment(commentId: Int): Boolean {
        val comment = comments.firstOrNull { it.id == commentId }
            ?: throw NoteCommentNotFoundException("Comment with id=$commentId not found")
        if (comment.isDeleted) {
            return false
        }
        comment.isDeleted = true
        return true
    }

    fun editComment(commentId: Int, message: String): Boolean {
        val comment = comments.firstOrNull { it.id == commentId }
            ?: throw NoteCommentNotFoundException("Comment with id=$commentId not found")
        if (comment.isDeleted) {
            return false
        }
        comment.message = message
        return true
    }

    fun getComments(noteId: Int, includeDeleted: Boolean = false): List<NoteComment> {
        requireNote(noteId, includeDeleted)
        return comments.asSequence()
            .filter { it.noteId == noteId }
            .filter { includeDeleted || !it.isDeleted }
            .map { it.copy() }
            .toList()
    }

    fun restoreComment(commentId: Int): Boolean {
        val comment = comments.firstOrNull { it.id == commentId }
            ?: throw NoteCommentNotFoundException("Comment with id=$commentId not found")
        if (!comment.isDeleted) {
            return false
        }
        if (notes.none { it.id == comment.noteId && !it.isDeleted }) {
            throw NoteNotFoundException("Note with id=${comment.noteId} not found or deleted")
        }
        comment.isDeleted = false
        return true
    }

    private fun requireNote(id: Int, includeDeleted: Boolean = false): Note {
        return notes.firstOrNull { it.id == id && (includeDeleted || !it.isDeleted) }
            ?: throw NoteNotFoundException("Note with id=$id not found")
    }
}