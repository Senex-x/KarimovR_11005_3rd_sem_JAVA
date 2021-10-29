package com.itis.stalkershop.web.servlets;

import com.itis.stalkershop.models.Image;
import com.itis.stalkershop.services.interfaces.ImageService;

import static com.itis.stalkershop.utils.UtilsKt.getAttribute;

import com.itis.stalkershop.utils.exceptions.NotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files/*")
public class ImagesServlet extends HttpServlet {
    private ImageService imageService;

    @Override
    public void init(
            ServletConfig config
    ) throws ServletException {
        super.init(config);

        imageService = getAttribute(
                ImageService.class,
                config.getServletContext()
        );
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
            Image fileInfo = imageService.getFileInfo(fileId);
            response.setContentType(fileInfo.getType());
            response.setContentLength((int) fileInfo.getSize());
            response.setHeader(
                    "Content-Disposition",
                    "filename=\"" + fileInfo.getOriginalName() + "\""
            );
            imageService.writeFileFromStorage(
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
