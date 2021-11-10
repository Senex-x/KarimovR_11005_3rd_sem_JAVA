package com.itis.stalkershop.services.implementations

import com.itis.stalkershop.models.UserDto
import com.itis.stalkershop.repositories.interfaces.UsersRepository
import com.itis.stalkershop.services.interfaces.ImageService
import com.itis.stalkershop.services.interfaces.UserService
import com.itis.stalkershop.utils.log
import com.itis.stalkershop.utils.updateSessionUser
import javax.servlet.http.HttpServletRequest

class MainUserService(
    private val usersRepository: UsersRepository,
    private val imageService: ImageService,
) : UserService {
    override fun update(user: UserDto) {
        usersRepository.update(
            user.email,
            user
        )
    }

    override fun deleteImage(request: HttpServletRequest, user: UserDto) {
        val updatedUser = user.copy(avatarId = null)
        usersRepository.update(
            updatedUser.email,
            updatedUser
        )
        imageService.deleteUserImageFromStorage(user)
        request.updateSessionUser(updatedUser)
    }
}