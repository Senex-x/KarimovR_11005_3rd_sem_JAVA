package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.CartDto;
import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.services.interfaces.CartService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;
import static com.itis.stalkershop.utils.SessionUtilKt.getSessionUser;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
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
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        CartDto cartDto = cartService.get(
                getSessionUser(request)
        );

        List<ItemDto> itemList = cartDto.getItemList();
        Collections.reverse(itemList);

        request.setAttribute("items", itemList);
        request.setAttribute("totalCost", cartDto.getTotalCost());
        request.getRequestDispatcher("/cart.ftl").forward(request, response);
    }
}