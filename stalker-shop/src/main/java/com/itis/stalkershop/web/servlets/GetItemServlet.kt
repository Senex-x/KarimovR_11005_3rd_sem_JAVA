package com.itis.stalkershop.web.servlets

import com.itis.stalkershop.services.interfaces.ItemService
import com.itis.stalkershop.utils.getAttribute
import com.itis.stalkershop.utils.logExt
import com.itis.stalkershop.utils.toJson
import java.io.IOException
import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/get-item")
class GetItemServlet : HttpServlet() {
    private lateinit var itemService: ItemService

    @Throws(ServletException::class)
    override fun init(
        config: ServletConfig
    ) {
        super.init(config)

        itemService = getAttribute(
            ItemService::class.java,
            config.servletContext
        )
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val item = itemService.get(request.getParameter("itemName"))

        logExt(item.toString())
        item?.also {
            response.writer.print(it.toJson())
        }
    }
}