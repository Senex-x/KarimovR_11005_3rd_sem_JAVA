package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.utils.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        ItemDto item = (ItemDto) request.getSession().getAttribute("item");
        LogKt.log(this, item.toString());

        request.setAttribute("items", List.of(item, item, item, item));
        request.getRequestDispatcher("/cart.ftl").forward(request, response);
    }
}
