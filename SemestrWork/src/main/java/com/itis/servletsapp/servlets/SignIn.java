package com.itis.servletsapp.servlets;

import javax.servlet.ServletException;
import com.itis.servletsapp.Database.UserRepository;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.itis.servletsapp.dao.base.CrudRepository;
import com.itis.servletsapp.model.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Optional;


@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/users";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (req.getSession(true).getAttribute("User") != null) {
            resp.sendRedirect("/profile");
        } else {
            req.getRequestDispatcher("signIn.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        CrudRepository<User, Long> userDao = new UserRepository(dataSource);

        String username = req.getParameter("username");
        String password = req.getParameter("password");


        Optional<User> optionalDriver = userDao.findById(username);

        if (optionalDriver.isEmpty()) {
            resp.sendRedirect("/sign-up");
            return;
        }

        User driver = optionalDriver.get();
        if (driver.getPassword().equals(password)) {
            req.getSession(true).setAttribute("User", username);
            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/sign-in");
        }
    }
}