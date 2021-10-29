package com.itis.stalkershop.web.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        super.doGet(req, resp);

    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
