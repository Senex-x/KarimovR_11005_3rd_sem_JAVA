package com.itis.stalkershop.web.servlets

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/item-info")
class ItemInfoServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        request
            .getRequestDispatcher("/item_info.ftl")
            .forward(request, response)
    }
}