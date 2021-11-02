package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.Cart;
import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.services.interfaces.CartService;
import com.itis.stalkershop.services.interfaces.ItemService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

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
        // Handle null values
        String userEmail = ((UserDto) request
                .getSession().getAttribute("user")
        ).getEmail();
        String itemName = request.getParameter("itemName");
        ItemDto itemDto = itemService.get(itemName);
        request.getSession().setAttribute("item", itemDto);

        cartService.addItem(
                userEmail, "itemName"
        );

        // Save cart somewhere
    }
}
