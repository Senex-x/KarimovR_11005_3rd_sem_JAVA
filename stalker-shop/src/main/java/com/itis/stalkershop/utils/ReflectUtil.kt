package com.itis.stalkershop.utils

fun Any.getClass() =
    this::class.java

@JvmName("getClassKt")
inline fun <reified T> getClass() =
    T::class.java

fun Any.getNameOfImplementedInterface(): String =
    getClass().interfaces[0].simpleName

fun Any.simpleName(): String =
    getClass().simpleName

inline fun <reified T> simpleName(): String =
    getClass<T>().simpleName