package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.utils.LogKt;
import com.itis.stalkershop.utils.UtilsKt;

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
        request.setCharacterEncoding("UTF-8");
        UserDto userDto = UtilsKt.getSessionUser(request);

        LogKt.log(this, "Received user: " + userDto);
        request.setAttribute("user", userDto);
        request.getRequestDispatcher("/profile.ftl")
                .forward(request, response);
    }
}
