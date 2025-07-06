package ru.netology

open class Photo(
    override var type: String,
    override var id: Int,
    override var ownerId: Int,
    override var date: Int,
    override var title: String,
    override var artist: String,
    override var duration: Int,
    override var description: String,
    override var size: Int,
    override var ext: String,

    ): Attachments() {
    override var userId: Int = 1
    override fun toString(): String {
        return "PHOTO"
    }
}