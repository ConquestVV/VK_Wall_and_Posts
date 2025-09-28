package ru.netology

interface CrudService<T> {
    fun add(entity: T): T
    fun delete(id: Int): Boolean
    fun edit(entity: T): Boolean
    fun get(id: Int): T?
}