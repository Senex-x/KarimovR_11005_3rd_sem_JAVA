package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.utils.LogKt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.SessionUtilKt.removeSessionUser;

@WebServlet("/sign-out")
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        removeSessionUser(request);

        LogKt.log(this, "Signing out");
        response.sendRedirect("/sign-in");
    }
}