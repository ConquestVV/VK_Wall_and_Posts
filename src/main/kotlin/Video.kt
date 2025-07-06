package ru.netology

open class Video(
    override var type: String,
    override var id: Int,
    override var ownerId: Int,
    override var userId: Int,
    override var date: Int,
    override var artist: String,
    override var size: Int,
    override var ext: String,
): Attachments() {
    override var title: String = "Cuttie cats"
    override var description: String = "meow"
    override var duration: Int = 120
    override fun toString(): String {
        return "VIDEO"
    }
}