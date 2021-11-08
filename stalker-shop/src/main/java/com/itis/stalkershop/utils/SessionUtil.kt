package com.itis.stalkershop.utils

import com.itis.stalkershop.models.UserDto
import org.springframework.asm.Attribute
import javax.servlet.http.HttpServletRequest

fun <T> HttpServletRequest.getSessionAttribute(name: String): T =
    session.getAttribute(name) as T

fun HttpServletRequest.setSessionAttribute(name: String, attr: Any) =
    session.setAttribute(name, attr)

fun HttpServletRequest.getSessionUser(): UserDto =
    getSessionAttribute(simpleName<UserDto>())

fun HttpServletRequest.setSessionUser(user: UserDto) =
    setSessionAttribute(simpleName<UserDto>(), user)

fun HttpServletRequest.removeSessionUser() =
    session.removeAttribute(getClass<UserDto>().simpleName)

fun HttpServletRequest.updateSessionUser(user: UserDto) {
    log(getSessionUser().toString())
    removeSessionUser()
    setSessionUser(user)
    log(getSessionUser().toString())
}