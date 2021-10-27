package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.utils.logger.LogKt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-out")
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        LogKt.log(this, "Signing out");
        request.getSession().removeAttribute("user");
        response.sendRedirect("/sign-in");
    }
}