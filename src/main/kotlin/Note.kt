package ru.netology

data class Note(
    var text: String,
    override var title: String,
    override var type: String,
    override var id: Int,
    override var ownerId: Int,
    override var userId: Int,
    override var artist: String,
    override var duration: Int,
    override var description: String,
    override var size: Int,
    override var ext: String,
    var isDeleted: Boolean = false

): Attachments() {

    override var date: Int = 15
    override fun toString(): String {
        return "$title: $text"
    }



}