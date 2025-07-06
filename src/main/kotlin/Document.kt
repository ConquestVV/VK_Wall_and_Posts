package ru.netology

open class Document(
    override var type: String,
    override var id: Int,
    override var ownerId: Int,
    override var userId: Int,
    override var date: Int,
    override var artist: String,
    override var duration: Int,
    override var description: String,
): Attachments() {
    override var title: String = "Microsoft Office Doc"
    override var size: Int = 100
    override var ext: String = "null"
    override fun toString(): String {
        return "DOCUMENT"
    }
}