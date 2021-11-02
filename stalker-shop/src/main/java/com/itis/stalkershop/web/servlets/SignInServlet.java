package com.itis.stalkershop.web.servlets;

import com.google.gson.Gson;
import com.itis.stalkershop.models.UserAuth;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.services.interfaces.SignInService;
import com.itis.stalkershop.utils.LogKt;
import com.itis.stalkershop.utils.exceptions.ValidationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        signInService = getAttribute(
                SignInService.class,
                config.getServletContext()
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        LogKt.log(
                this,
                new Gson().toJson(List.of("asfasf", "yfhkasf"))
        );

        request.getRequestDispatcher("sign_in.ftl")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        UserAuth userAuth = new UserAuth(
                request.getParameter("email"),
                request.getParameter("password")
        );

        // Debug-only
        userAuth = new UserAuth(
                "vdm.snx@gmail.com",
                "password"
        );

        LogKt.log(this, "Trying to authenticate user: " + userAuth);

        UserDto userDto;

        // TODO: refactor try/catch
        try {
            userDto = signInService.signIn(userAuth);
            LogKt.log(this, "Authentication succeed");
        } catch (ValidationException e) {
            response.sendRedirect("/sign-in");
            LogKt.log(this, "Authentication failed");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", userDto);
        response.sendRedirect("/profile");
    }
}
