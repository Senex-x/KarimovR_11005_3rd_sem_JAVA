package com.itis.servletsapp.servlets;

import com.itis.servletsapp.Database.FileInfo;
import com.itis.servletsapp.Database.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

//TODO: дз на 16.10
@WebServlet("/file-upload")
@MultipartConfig
public class FilesUpload extends HttpServlet {
    String PATH = "";
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.filesService = (FilesService) config.getServletContext().getAttribute("file");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("fileUpload.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        FileInfo fileInfo = filesService.saveFileToStorage(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());
        resp.sendRedirect("/files/" + fileInfo.getId());

    }

    //    private final FilesRepository filesRepository;
}
