package com.itis.stalkershop.repositories.interfaces

// K - PRIMARY KEY type
// T - Item model type
interface ReworkedRepository<K, T> {
    fun add(item: T)

    /**
     * Returns an item obtained from repository by its primary key,
     * or `null` if it not presents.
     * Supports Kotlin operator access syntax.
     *
     * @return Item from repository or `null`
     */
    operator fun get(primaryKey: K): T?

    fun getAll(): List<T>

    fun update(item: T)

    fun delete(primaryKey: K)
}