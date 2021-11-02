package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.services.interfaces.CartService;
import com.itis.stalkershop.services.interfaces.ItemService;
import com.itis.stalkershop.utils.UtilsKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;
import static com.itis.stalkershop.utils.UtilsKt.getTypedAttribute;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private ItemService itemService;
    private CartService cartService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        itemService = getAttribute(
                ItemService.class,
                config.getServletContext()
        );
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
        UserDto user = getTypedAttribute(request, "user"); // Unchecked nullable
        String userEmail = user.getEmail();
        String itemName = request.getParameter("itemName");

        cartService.addItem(
                userEmail, itemName
        );
    }
}
