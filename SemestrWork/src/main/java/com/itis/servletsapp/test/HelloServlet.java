package com.itis.servletsapp.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

//@WebServlet("/hello")
@WebServlet("/addcookie")
public class HelloServlet extends HttpServlet {
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//////        System.out.println(request.getHeader("FirstHeader"));
//////        response.getWriter().println("Hello");
////        String randomString = UUID.randomUUID().toString();
//////        response.getWriter().println(randomString);
////
////        Cookie cookie = new Cookie("Message", randomString);
////        cookie.setMaxAge(10); // seconds
////        response.addCookie(cookie);
////        response.getWriter().println(cookie);
//    }
}
