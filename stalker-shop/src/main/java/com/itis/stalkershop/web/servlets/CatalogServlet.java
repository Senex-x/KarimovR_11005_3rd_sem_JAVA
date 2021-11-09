package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.ItemDto;
import com.itis.stalkershop.services.interfaces.ItemService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    private ItemService itemService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        itemService = getAttribute(
                ItemService.class,
                config
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<ItemDto> items = itemService.getAll();
        request.setAttribute("items", items);
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/catalog.ftl")
                .forward(request, response);
    }
}
