package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.services.interfaces.TokenService;
import com.itis.stalkershop.utils.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.SessionUtilKt.getSessionUser;
import static com.itis.stalkershop.utils.SessionUtilKt.removeSessionUser;
import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

@WebServlet("/sign-out")
public class SignOutServlet extends HttpServlet {
    private TokenService tokenService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        tokenService = getAttribute(
                TokenService.class,
                config
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        tokenService.delete(
                getSessionUser(request)
        );
        removeSessionUser(request);

        LogKt.log(this, "Signing out");
        response.sendRedirect("/sign-in");
    }
}