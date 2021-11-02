package com.itis.stalkershop.utils

import javax.servlet.ServletContext

// For use from Java
fun <T> getAttribute(type: Class<T>, context: ServletContext) =
    context.getAttribute(type.simpleName) as T

// For use from Kotlin
inline fun <reified T> ServletContext.getAttribute() =
    getAttribute(getSimpleNameOf<T>()) as T

fun getSimpleNameOf(item: Any) =
    item::class.java.simpleName

inline fun <reified T> getSimpleNameOf() =
    T::class.java.simpleName





