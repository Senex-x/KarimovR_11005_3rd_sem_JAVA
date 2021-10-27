package com.itis.stalkershop.web.context;

import com.itis.stalkershop.repositories.implementations.FilesRepositoryMain;
import com.itis.stalkershop.repositories.implementations.UsersRepositoryMain;
import com.itis.stalkershop.repositories.interfaces.FilesRepository;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import com.itis.stalkershop.services.implementations.*;
import com.itis.stalkershop.services.interfaces.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomContextListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(
            ServletContextEvent servletContextEvent
    ) {
        ServletContext servletContext =
                servletContextEvent.getServletContext();
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        FilesRepository filesRepository = new FilesRepositoryMain(
                dataSource
        );
        ImageService imageService = new ImageServiceMain(
                filesRepository
        );
        UsersRepository usersRepository = new UsersRepositoryMain(
                dataSource
        );
        PasswordService passwordService = new PasswordServiceMain();
        SignInService signInService = new SignInServiceMain(
                usersRepository,
                passwordService
        );
        ValidationService validationService = new ValidationServiceMain(
                usersRepository
        );
        SignUpService signUpService = new SignUpServiceMain(
                usersRepository,
                passwordService,
                validationService
        );
        servletContext.setAttribute("filesRepository", filesRepository);
        servletContext.setAttribute("filesService", imageService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("usersRepository", usersRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
