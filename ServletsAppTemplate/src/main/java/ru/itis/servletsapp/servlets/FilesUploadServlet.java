package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dao.UsersRepository;
import ru.itis.servletsapp.dto.UserDto;
import ru.itis.servletsapp.model.FileInfo;
import ru.itis.servletsapp.model.User;
import ru.itis.servletsapp.services.FilesService;

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
        this.filesService = (FilesService) config.getServletContext().getAttribute("filesService");
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("fileUpload.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        FileInfo fileInfo = filesService.saveFileToStorage(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());

        UserDto user = (UserDto) request.getAttribute("user");
        User currentUser = usersRepository.findById(user.getId()).get();
        usersRepository.update(
                user.getId(),
                new User(
                        currentUser.getId(),
                        currentUser.getFirstName(),
                        currentUser.getLastName(),
                        currentUser.getHashPassword(),
                        currentUser.getEmail(),
                        currentUser.getAge(),
                        fileInfo.getId()
                )
        );

//        filesService.saveFileToStorage(fileInfo);


        response.sendRedirect("/files/" + fileInfo.getId());
    }
}
