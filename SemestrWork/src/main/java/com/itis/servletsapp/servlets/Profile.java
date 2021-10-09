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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (req.getSession(true).getAttribute("User") != null){
            String username = (String) req.getSession().getAttribute("User");
            System.out.println("User: " + username);
            req.setAttribute("username", username);
            req.getRequestDispatcher("profile.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/sign-in");
        }
    }
}
