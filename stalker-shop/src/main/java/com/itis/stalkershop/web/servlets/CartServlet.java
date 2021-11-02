package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.CartDto;
import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.services.interfaces.CartService;
import com.itis.stalkershop.utils.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

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
        ItemDto item = (ItemDto) request.getSession().getAttribute("item");
        LogKt.log(this, item.toString());

        CartDto cartDto = cartService.get(
                ((UserDto) request
                        .getSession()
                        .getAttribute("user")
                ).getEmail()
        );

        LogKt.log(this, cartDto.toString());

        request.setAttribute("items", List.of(item, item, item, item));
        request.getRequestDispatcher("/cart.ftl").forward(request, response);
    }
}
