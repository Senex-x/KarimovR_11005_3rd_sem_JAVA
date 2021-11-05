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
    private List<ItemDto> items;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        ItemService itemService = getAttribute(
                ItemService.class,
                config.getServletContext()
        );

        items = itemService.getAll();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        for (ItemDto item : items) {
            request.setAttribute(
                    item.getName()
                            .toLowerCase(Locale.ROOT)
                            .replaceAll("[-' ]", ""),
                    item
            );
        }

        request.setAttribute("items", items);
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/test/catalog-test-2.ftl")
                .forward(request, response);
    }
}
