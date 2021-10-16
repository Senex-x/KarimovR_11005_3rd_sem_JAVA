package com.itis.servletsapp.servlets;

import com.itis.servletsapp.dao.base.CrudRepository;
import com.itis.servletsapp.model.User;
import com.itis.servletsapp.Database.UserRepository;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUp extends HttpServlet {
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
            req.getRequestDispatcher("signUp.ftl").forward(req, resp);
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        CrudRepository<User, Long> userDao = new UserRepository(dataSource);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (userDao.findById(username).isPresent()) {
            resp.sendRedirect("/sign-up");
            return;
        }

        if (!passwordEmpty(password) || !usernameEmpty(username)) {
            resp.sendRedirect("/sign-up");
            return;
        }

        User driver = User.builder()
                .username(username)
                .password(password)
                .build();

        userDao.save(driver);

        req.getSession(true).setAttribute("User", username);
        resp.sendRedirect("/profile");

    }

    private Boolean passwordEmpty(String password) {
        return !password.equals("");
    }

    private Boolean usernameEmpty(String username) {
        return !username.equals("");
    }


}