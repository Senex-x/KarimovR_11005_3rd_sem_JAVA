package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.Image;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.ImageService;
import com.itis.stalkershop.utils.logger.LogKt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

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
        imageService = (ImageService) config
                .getServletContext()
                .getAttribute("filesService");
        usersRepository = (UsersRepository) config
                .getServletContext()
                .getAttribute("usersRepository");
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("image_upload.ftl")
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

        LogKt.log(this, "Trying get user from session");
        UserDto user = (UserDto) request.getSession().getAttribute("user");
        LogKt.log(this, "Got user from session: " + user);


        //System.out.println("DEBUG2_1 " + usersRepository.findByEmail(user.getEmail()));

        // Why the fuck do we use Optional then?
        //User currentUser = usersRepository.findByEmail(user.getEmail()).get();

        //System.out.println("DEBUG2 CURRENTUSER: " + currentUser);
        //System.out.println("DEBUG3 FILEINFO: " + fileInfo.getId());
        usersRepository.update(
                user.getEmail(),
                new UserDto(
                        user.getEmail(),
                        user.getName(),
                        user.getPasswordHash(),
                        newFile.getId()
                )
        );

//  ??      filesService.saveFileToStorage(fileInfo);

        response.sendRedirect("/files/" + newFile.getId());
    }
}
