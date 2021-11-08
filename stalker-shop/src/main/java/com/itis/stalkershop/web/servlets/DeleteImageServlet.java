package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.services.interfaces.UserService;
import com.itis.stalkershop.utils.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;
import static com.itis.stalkershop.utils.SessionUtilKt.getSessionUser;

@WebServlet("/delete-image")
public class DeleteImageServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        userService = getAttribute(
                UserService.class,
                config.getServletContext()
        );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        LogKt.log(this, "Deleting image");
        userService.deleteImage(request, getSessionUser(request));
    }
}
