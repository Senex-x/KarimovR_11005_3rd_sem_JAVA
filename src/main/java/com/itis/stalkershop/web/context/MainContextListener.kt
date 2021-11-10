package com.itis.stalkershop.web.context

import com.itis.stalkershop.repositories.implementations.*
import com.itis.stalkershop.repositories.interfaces.*
import com.itis.stalkershop.services.implementations.*
import com.itis.stalkershop.services.interfaces.*
import com.itis.stalkershop.utils.getNameOfImplementedInterface
import com.itis.stalkershop.utils.getTyped
import com.itis.stalkershop.utils.loadPropertiesFrom
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

private const val PROPERTIES_FILE_NAME = "application.properties"

@WebListener
class MainContextListener : ServletContextListener {
    override fun contextInitialized(
        servletContextEvent: ServletContextEvent
    ) {
        val dbUsername: String
        val dbPassword: String
        val dbUrl: String
        val dbDriver: String
        val imageStoragePath: String

        loadPropertiesFrom(PROPERTIES_FILE_NAME).apply {
            dbUsername = getTyped("spring.datasource.username")
            dbPassword = getTyped("spring.datasource.password")
            dbUrl = getTyped("spring.datasource.url")
            dbDriver = getTyped("spring.datasource.driver-class-name")
            imageStoragePath = getTyped("storage.path.images")
        }

        val dataSource = DriverManagerDataSource().apply {
            setDriverClassName(dbDriver)
            username = dbUsername
            password = dbPassword
            url = dbUrl
        }

        val imageRepository: ImageRepository = MainImageRepository(
            dataSource
        )
        val imageService: ImageService =
            MainImageService(
                imageStoragePath,
                imageRepository
            )
        val userRepository: UsersRepository =
            MainUsersRepository(
                dataSource
            )
        val userService: UserService =
            MainUserService(
                userRepository,
                imageService
            )
        val passwordService: PasswordService =
            MainPasswordService()
        val tokenRepository: TokenRepository =
            MainTokenRepository(
                dataSource
            )
        val tokenService: TokenService =
            MainTokenService(
                tokenRepository
            )
        val authService: AuthService =
            MainAuthService(
                userRepository,
                tokenService,
                passwordService
            )
        val validationService: ValidationService =
            MainValidationService(
                userRepository
            )
        val signUpService: SignUpService =
            MainSignUpService(
                userRepository,
                passwordService,
                validationService,
                tokenService
            )
        val itemsRepository: ItemsRepository =
            MainItemsRepository(
                dataSource
            )
        val itemService: ItemService =
            MainItemService(
                itemsRepository
            )
        val cartRepository: CartRepository =
            MainCartRepository(
                dataSource
            )
        val cartService: CartService =
            MainCartService(
                cartRepository,
                itemsRepository
            )

        val attributes = listOf(
            userService,
            tokenService,
            imageRepository,
            imageService,
            authService,
            signUpService,
            userRepository,
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
}