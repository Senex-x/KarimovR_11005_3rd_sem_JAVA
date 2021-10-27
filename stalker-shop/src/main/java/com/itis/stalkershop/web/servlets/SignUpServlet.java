package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UserRegister;
import com.itis.stalkershop.services.interfaces.SignUpService;
import com.itis.stalkershop.utils.exceptions.ErrorEntity;
import com.itis.stalkershop.utils.exceptions.ValidationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config
                .getServletContext()
                .getAttribute("signUpService");
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("sign_up.ftl").forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        UserRegister form;
        try {
            form = new UserRegister(
                    request.getParameter("email"),
                    request.getParameter("name"),
                    request.getParameter("password")
            );

        } catch (NumberFormatException e) {
            Set<ErrorEntity> errors = new HashSet<>();
            errors.add(ErrorEntity.INVALID_REQUEST);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("sign_in.ftl").forward(request, response);
            return;
        }

        try {
            signUpService.signUp(form);
        } catch (ValidationException e) {
            request.setAttribute("error", e.getEntity());
            request.getRequestDispatcher("sign_up.ftl").forward(request, response);
            return;
        }
        response.sendRedirect("/sign-in");
    }
}
