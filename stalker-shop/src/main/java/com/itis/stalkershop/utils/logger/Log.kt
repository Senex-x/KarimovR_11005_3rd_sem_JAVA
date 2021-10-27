package com.itis.stalkershop.utils.logger

import javax.servlet.http.HttpServlet

private const val LOG_PREFIX = "app-debug: "

fun log(message: String?) =
    println("$LOG_PREFIX$message")

@JvmName("logExt")
fun String?.log() =
    log(this)

fun log(context: String, message: String) =
    println("$LOG_PREFIX$context: $message")

fun log(context: HttpServlet, message: String?) =
    println("$LOG_PREFIX${context.servletName}: $message")

fun HttpServlet.logExt(message: String?) =
    log(this, message)
