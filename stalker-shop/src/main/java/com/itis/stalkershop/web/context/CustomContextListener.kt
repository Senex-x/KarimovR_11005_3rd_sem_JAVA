package com.itis.stalkershop.web.context

import com.itis.stalkershop.repositories.implementations.CartRepositoryMain
import com.itis.stalkershop.repositories.implementations.FilesRepositoryMain
import com.itis.stalkershop.repositories.implementations.ItemsRepositoryMain
import com.itis.stalkershop.repositories.implementations.UsersRepositoryMain
import com.itis.stalkershop.repositories.interfaces.CartRepository
import com.itis.stalkershop.repositories.interfaces.FilesRepository
import com.itis.stalkershop.repositories.interfaces.ItemsRepository
import com.itis.stalkershop.repositories.interfaces.UsersRepository
import com.itis.stalkershop.services.implementations.*
import com.itis.stalkershop.services.interfaces.*
import com.itis.stalkershop.utils.getNameOfImplementedInterface
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

private const val DB_USERNAME = "postgres"
private const val DB_PASSWORD = "postgres"
private const val DB_URL = "jdbc:postgresql://localhost:5432/postgres"
private const val DB_DRIVER = "org.postgresql.Driver"

@WebListener
class CustomContextListener : ServletContextListener {
    override fun contextInitialized(
        servletContextEvent: ServletContextEvent
    ) {
        val dataSource = DriverManagerDataSource().apply {
            setDriverClassName(DB_DRIVER)
            username = DB_USERNAME
            password = DB_PASSWORD
            url = DB_URL
        }

        val filesRepository: FilesRepository = FilesRepositoryMain(
            dataSource
        )
        val imageService: ImageService = ImageServiceMain(
            filesRepository
        )
        val usersRepository: UsersRepository = UsersRepositoryMain(
            dataSource
        )
        val passwordService: PasswordService = PasswordServiceMain()
        val signInService: SignInService = SignInServiceMain(
            usersRepository,
            passwordService
        )
        val validationService: ValidationService = ValidationServiceMain(
            usersRepository
        )
        val signUpService: SignUpService = SignUpServiceMain(
            usersRepository,
            passwordService,
            validationService
        )
        val itemsRepository: ItemsRepository = ItemsRepositoryMain(
            dataSource
        )
        val itemService: ItemService = ItemServiceMain(
            itemsRepository
        )
        val cartRepository: CartRepository = CartRepositoryMain(
            dataSource
        )
        val cartService: CartService = CartServiceMain(
            cartRepository,
            itemsRepository
        )

        val attributes = listOf(
            filesRepository,
            imageService,
            signInService,
            signUpService,
            usersRepository,
            itemService,
            cartService
        )

        val context = servletContextEvent.servletContext
        for (attribute in attributes) {
            context.setAttribute(
                attribute.getNameOfImplementedInterface(),
                attribute
            )
        }
    }

    override fun contextDestroyed(servletContextEvent: ServletContextEvent) {}
}