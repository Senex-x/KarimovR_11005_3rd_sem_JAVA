package com.itis.servletsapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getSession(true).getAttribute("User") != null) {
            String username = String.valueOf(request.getSession().getAttribute("User"));
            System.out.println("USER: " + username);
            request.setAttribute("username", username);
            request.getRequestDispatcher("profile.ftl").forward(request, response);
        } else {
            response.sendRedirect("/sign-in");
        }
    }
}