package com.itis.stalkershop.web.servlets

import com.itis.stalkershop.models.UserAuth
import com.itis.stalkershop.models.UserDto
import com.itis.stalkershop.services.interfaces.AuthService
import com.itis.stalkershop.services.interfaces.TokenService
import com.itis.stalkershop.utils.*
import com.itis.stalkershop.utils.exceptions.ValidationException
import java.io.IOException
import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/sign-in")
class SignInServlet : HttpServlet() {
    private lateinit var authService: AuthService
    private lateinit var tokenService: TokenService

    @Throws(ServletException::class)
    override fun init(
        config: ServletConfig
    ) {
        super.init(config)

        authService = config.getAttribute()
        tokenService = config.getAttribute()
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        request
            .getRequestDispatcher("sign_in.ftl")
            .forward(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {

        val userAuth = UserAuth(
            request.getParameter("email"),
            request.getParameter("password")
        )

        authService.auth(
            request,
            response,
            userAuth,
            getAuthResultHandler(request, response)
        )
    }

    private fun getAuthResultHandler(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) = object : AuthService.AuthCallback {

        override fun onSuccess(userDto: UserDto) {
            response.sendRedirect("/profile")
            logExt("Authentication succeed")
        }

        override fun onFail(exception: ValidationException) {
            response.sendRedirect("/sign-in")
            logExt("Authentication failed with exception: $exception")
        }
    }
}