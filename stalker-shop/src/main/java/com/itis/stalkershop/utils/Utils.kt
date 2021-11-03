package com.itis.stalkershop.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis.stalkershop.models.UserDto
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest

// For use from Kotlin
@JvmName("getAttributeKt")
inline fun <reified T> ServletContext.getAttribute() =
    getAttribute(simpleName<T>()) as T

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

fun Any.simpleName(): String =
    getClass().simpleName

inline fun <reified T> simpleName(): String =
    getClass<T>().simpleName

fun <T> HttpServletRequest.getSessionAttribute(name: String): T? =
    session.getAttribute(name) as? T

fun HttpServletRequest.getSessionUser(): UserDto =
    session.getAttribute(getClass<UserDto>().simpleName) as UserDto

private val gson = Gson()

typealias JsonString = String

@JvmName("jsonToList")
fun <T> JsonString.toListOf(): List<T> =
    gson.fromJson(
        this,
        object : TypeToken<List<T>>() {}.type
    )

fun <T> List<T>.toJson(): String =
    gson.toJson(this)











