package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        // request.setAttribute("user", new User(null, "Ivan Ivanov", 6L));

        request.setCharacterEncoding("UTF-8");
        UserDto userDto = (UserDto) request
                .getSession()
                .getAttribute("user");
        System.out.println("FINDER" + userDto);
        request.setAttribute("user", userDto);
        request.getRequestDispatcher("/profile.ftl")
                .forward(request, response);
    }
}
