package com.itis.stalkershop.utils

import java.io.IOException
import java.util.*
import javax.servlet.ServletConfig
import javax.servlet.ServletContextListener

// For use from Kotlin
@JvmName("getAttributeKt")
inline fun <reified T> ServletConfig.getAttribute() =
    servletContext.getAttribute(simpleName<T>()) as T

// For use from Java
fun <T> getAttribute(type: Class<T>, config: ServletConfig) =
     config.servletContext.getAttribute(type.simpleName) as T

fun ServletContextListener.loadPropertiesFrom(fileName: String) = try {
    Properties().apply {
        load(
            this@loadPropertiesFrom.javaClass.classLoader
                .getResourceAsStream(fileName)
        )
    }
} catch (exception: IOException) {
    throw RuntimeException("$fileName file is missing", exception)
}

fun <T> Properties.getTyped(propertyName: String) =
    get(propertyName) as T






