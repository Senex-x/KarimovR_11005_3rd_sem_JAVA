package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.services.implementations.ItemServiceMain;
import com.itis.stalkershop.services.interfaces.ItemService;
import com.itis.stalkershop.utils.UtilsKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

@WebServlet("/catalog")
public class ShopServlet extends HttpServlet {
    private static final String[] itemNames = {
            "Cossacks vodka",
            "Tourist's Delight",
            "Bread"
    };
    private ItemService itemService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        itemService = getAttribute(
                ItemService.class,
                config.getServletContext()
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        super.doGet(request, response);

        for (String itemName : itemNames) {
            request.setAttribute(
                    itemName,
                    itemService.getItemDto(itemName
                            .toLowerCase(Locale.ROOT)
                            .replaceAll(" ", "-")
                    )
            );
        }

        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/catalog.ftl")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        super.doPost(request, response);

        //request.setAttribute("varName", variable);
        //getServletContext().getRequestDispatcher("servlet2").forward(request,response);
    }
}
