package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UserAuth;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.services.interfaces.SignInService;
import com.itis.stalkershop.utils.exceptions.ValidationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config
                .getServletContext()
                .getAttribute("signInService");
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("sign_in.ftl")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
//        UserForm form = new UserForm(
//                request.getParameter("email"),
//                null, null,
//                request.getParameter("password"),
//                null
//        );
        UserAuth form = new UserAuth(
                request.getParameter("email"),
                request.getParameter("password")
        );

        UserDto userDto;

        // TODO: refactor try/catch
        try {
            userDto = signInService.signIn(form);
        } catch (ValidationException e) {
            response.sendRedirect("/sign-in");
            return;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("user", userDto);
        response.sendRedirect("/profile");
    }
}
