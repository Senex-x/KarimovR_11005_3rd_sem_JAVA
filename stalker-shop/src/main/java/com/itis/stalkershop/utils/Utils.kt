package com.itis.stalkershop.utils

import javax.servlet.ServletContext

// For use from Kotlin
@JvmName("getAttributeKt")
inline fun <reified T> ServletContext.getAttribute() =
    getAttribute(name<T>()) as T

// For use from Java
fun <T> getAttribute(type: Class<T>, context: ServletContext) =
    context.getAttribute(type.simpleName) as T

fun Any.getClass() =
    this::class.java

@JvmName("getClassKt")
inline fun <reified T> getClass() =
    T::class.java

fun Any.getNameOfImplementedInterface() =
    getClass().interfaces[0].simpleName

fun Any.name(): String =
    getClass().simpleName

inline fun <reified T> name(): String =
    getClass<T>().simpleName





