package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.services.interfaces.CartService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.UtilsKt.*;

@WebServlet("/delete-cart")
public class DeleteCartServlet extends HttpServlet {
    private CartService cartService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        cartService = getAttribute(
                CartService.class,
                config.getServletContext()
        );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        UserDto user = com.itis.stalkershop.utils.SessionUtilKt.getSessionUser(request);

        String userEmail = user.getEmail();
        cartService.delete(userEmail);
    }
}
