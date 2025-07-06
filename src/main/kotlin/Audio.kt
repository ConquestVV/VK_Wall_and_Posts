package ru.netology

open class Audio(
    override var type: String,
    override var id: Int,
    override var ownerId: Int,
    override var userId: Int,
    override var date: Int,
    override var description: String,
    override var size: Int,
    override var ext: String,
) : Attachments(){
    override var title: String = "HUMBLE."
    override var artist: String = "Kendrick Lamar"
    override var duration: Int = 120
    override fun toString(): String {
        return "AUDIO"
    }
}