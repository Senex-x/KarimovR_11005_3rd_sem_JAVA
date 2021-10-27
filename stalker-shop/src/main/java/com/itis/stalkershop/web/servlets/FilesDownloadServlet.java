package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.UploadedFile;
import com.itis.stalkershop.services.interfaces.FilesService;
import com.itis.stalkershop.utils.exceptions.NotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files/*")
public class FilesDownloadServlet extends HttpServlet {
    private FilesService filesService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);
        this.filesService = (FilesService) config
                .getServletContext()
                .getAttribute("filesService");
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        long fileId;
        try {
            String fileIdString = request.getRequestURI().substring(7);
            fileId = Long.parseLong(fileIdString);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            response.setStatus(400);
            response.getWriter().println("Wrong request");
            return;
        }

        try {
            UploadedFile fileInfo = filesService.getFileInfo(fileId);
            response.setContentType(fileInfo.getType());
            response.setContentLength((int) fileInfo.getSize());
            response.setHeader(
                    "Content-Disposition",
                    "filename=\"" + fileInfo.getOriginalFileName() + "\""
            );
            filesService.writeFileFromStorage(
                    fileId,
                    response.getOutputStream()
            );
            response.flushBuffer();
        } catch (NotFoundException e) {
            response.setStatus(404);
            response.getWriter().println(e.getMessage());
        }
    }
}
