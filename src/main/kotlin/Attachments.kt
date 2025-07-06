package ru.netology

abstract class Attachments {
    abstract var type: String
    abstract var id: Int
    abstract var ownerId: Int
    abstract var userId: Int
    abstract var date: Int
    abstract var title: String
    abstract var artist: String
    abstract var duration: Int
    abstract var description: String
    abstract var size: Int
    abstract var ext: String

    override fun toString(): String {
        return "Post (id = $id, ownerId = $ownerId, type = $type)"
    }
}