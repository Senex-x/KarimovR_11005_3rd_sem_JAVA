package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.utils.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        // No arguments received


        LogKt.log(this, request.getParameter("content"));
        LogKt.log(this, Integer.toString(request.getParameterMap().size()));
        request.getParameterMap().forEach((s, strings) -> {
            LogKt.log(s);
            Arrays.stream(strings).forEach(LogKt::log);
        });

        LogKt.log(this, request.getContentType());
        LogKt.log(this, Boolean.toString(request.getParameterNames().hasMoreElements()));

        Iterator<String> stringIterator = request.getParameterNames().asIterator();
        while (stringIterator.hasNext()) {
            LogKt.log(this, stringIterator.next());
        }

        response.getWriter().write("Response");
    }
}
