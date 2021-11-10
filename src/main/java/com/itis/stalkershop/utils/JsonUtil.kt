package com.itis.stalkershop.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

typealias JsonString = String

private val gson = Gson()

@JvmName("jsonToList")
fun <T> JsonString.toListOf(): List<T> =
    gson.fromJson(
        this,
        object : TypeToken<List<T>>() {}.type
    )

fun <T> List<T>.toJson(): JsonString =
    gson.toJson(this)

fun Any.toJson(): JsonString =
    gson.toJson(this)