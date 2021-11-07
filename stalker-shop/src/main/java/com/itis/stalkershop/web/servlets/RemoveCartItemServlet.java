package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.services.interfaces.CartService;
import com.itis.stalkershop.utils.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;
import static com.itis.stalkershop.utils.UtilsKt.getSessionUser;

@WebServlet("/remove-cart-item")
public class RemoveCartItemServlet extends HttpServlet {
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
        String userEmail = getSessionUser(request).getEmail();
        String itemName =  request.getParameter("itemName");

        LogKt.log(this, itemName);
        cartService.deleteItem(userEmail, itemName);
    }
}

