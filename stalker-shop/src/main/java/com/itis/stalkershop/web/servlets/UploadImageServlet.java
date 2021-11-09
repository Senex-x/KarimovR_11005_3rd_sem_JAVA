package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.Image;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.ImageService;
import com.itis.stalkershop.utils.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static com.itis.stalkershop.utils.UtilsKt.*;

@WebServlet("/upload-image")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {
    private ImageService imageService;
    private UsersRepository usersRepository;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        imageService = getAttribute(
                ImageService.class,
                config
        );
        usersRepository = getAttribute(
                UsersRepository.class,
                config
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("upload_image.ftl")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        Part part = request.getPart("file");
        Image newFile = imageService.saveFileToStorage(
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize()
        );
        LogKt.log(this, "Uploaded file: " + newFile);

        UserDto user = com.itis.stalkershop.utils.SessionUtilKt.getSessionUser(request);

        UserDto updatedUser = new UserDto(
                user.getEmail(),
                user.getName(),
                user.getPasswordHash(),
                newFile.getId()
        );

        usersRepository.update(
                updatedUser.getEmail(),
                updatedUser
        );
        com.itis.stalkershop.utils.SessionUtilKt.updateSessionUser(request, updatedUser);

//  ????      filesService.saveFileToStorage(fileInfo);
        response.sendRedirect("/profile");
    }
}
