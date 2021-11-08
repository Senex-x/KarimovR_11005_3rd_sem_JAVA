package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UserRegister;
import com.itis.stalkershop.services.interfaces.SignUpService;
import com.itis.stalkershop.utils.LogKt;
import com.itis.stalkershop.utils.exceptions.ValidationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        signUpService = getAttribute(
                SignUpService.class,
                config.getServletContext()
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("sign_up.ftl")
                .forward(request, response);
    }

    // TODO:
    //  refactor try/catch blocks
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        UserRegister userRegister = new UserRegister(
                request.getParameter("email"),
                request.getParameter("name"),
                request.getParameter("password")
        );

        LogKt.log(this, "Trying to register user: " + userRegister);

        try {
            signUpService.signUp(userRegister);
            LogKt.log(this, "Registration succeed");
        } catch (ValidationException e) {
            request.setAttribute("error", e.getEntity());
            request.getRequestDispatcher("sign_up.ftl")
                    .forward(request, response);
            LogKt.log(this, "Registration failed");
            return;
        }
        response.sendRedirect("/sign-in");
    }
}
