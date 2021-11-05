package com.itis.stalkershop.repositories.interfaces

import java.util.*

// Shit is happening here, but I don't want to refactor it at this point
// K - PK type, T - received item type, R - returned item type
interface Repository<K, in T, R> {
    fun findByPrimaryKey(primaryKey: K): Optional<R>

    fun findAll(): List<R>

    // Why the fuck is it returning an item itself?????
    fun save(item: T): R

    fun update(primaryKey: K, item: T)

    fun delete(primaryKey: K)
}
