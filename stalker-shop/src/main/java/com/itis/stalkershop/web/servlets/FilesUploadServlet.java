package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UploadedFile;
import com.itis.stalkershop.models.User;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.interfaces.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/file-upload")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {
    private FilesService filesService;
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) {
        filesService = (FilesService) config
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
        request.getRequestDispatcher("fileUpload.ftl").forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        Part part = request.getPart("file");
        UploadedFile fileInfo = filesService.saveFileToStorage(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());

        UserDto user = (UserDto) request.getSession().getAttribute("user");
//        System.out.println("DEBUG1 " + user);
//        System.out.println("DEBUG2_2 USERGETID " + user.getId());
        System.out.println("DEBUG2_1 " + usersRepository.findByEmail(user.getEmail()));

        // Why the fuck do we use Optional then?
        User currentUser = usersRepository.findByEmail(user.getEmail()).get();
        System.out.println("DEBUG2 CURRENTUSER: " + currentUser);
        System.out.println("DEBUG3 FILEINFO: " + fileInfo.getId());
        usersRepository.update(
                user.getEmail(),
                new UserDto(
                        currentUser.getEmail(),
                        currentUser.getName(),
                        currentUser.getPasswordHash(),
                        fileInfo.getId()
                )
        );

//  ??      filesService.saveFileToStorage(fileInfo);

        response.sendRedirect("/files/" + fileInfo.getId());
    }
}
