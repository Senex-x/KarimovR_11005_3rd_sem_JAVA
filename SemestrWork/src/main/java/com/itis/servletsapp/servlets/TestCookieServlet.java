package com.itis.servletsapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet("/check-cookie")
public class TestCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Cookie> optionalMessageCookie = Arrays.stream(request.getCookies() == null ? new Cookie[]{} : request.getCookies())
                .filter(item -> item.getName().equals("Message"))
                .findFirst();

        if (optionalMessageCookie.isPresent()){
            Cookie messageCookie = optionalMessageCookie.get();
            response.getWriter().println(messageCookie.getValue());
        } else {
            response.getWriter().println("No message");
        }
    }
}
