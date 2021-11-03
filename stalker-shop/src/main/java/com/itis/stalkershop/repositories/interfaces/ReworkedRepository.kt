package com.itis.stalkershop.repositories.interfaces

import java.util.*

// K - PRIMARY KEY type
// T - Item model type
interface ReworkedRepository<K, T> {
    fun add(item: T)
    fun get(primaryKey: K): T?
    fun getAll(): List<T>
    fun update(item: T)
    fun delete(primaryKey: K)
}