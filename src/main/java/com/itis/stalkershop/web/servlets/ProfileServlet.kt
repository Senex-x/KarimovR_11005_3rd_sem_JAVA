package com.itis.stalkershop.web.servlets

import com.itis.stalkershop.utils.getSessionUser
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/profile")
class ProfileServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        request.apply {
            characterEncoding = "UTF-8"
            setAttribute("user", getSessionUser())

            getRequestDispatcher("/profile.ftl")
                .forward(this, response)
        }
    }
}