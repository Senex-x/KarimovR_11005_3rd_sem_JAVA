package com.itis.stalkershop.services.implementations

import com.itis.stalkershop.models.UserDto
import com.itis.stalkershop.repositories.interfaces.UsersRepository
import com.itis.stalkershop.services.interfaces.ImageService
import com.itis.stalkershop.services.interfaces.UserService

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

    override fun deleteImage(user: UserDto) {
        usersRepository.update(
            user.email,
            user.copy(avatarId = null)
        )
        imageService.deleteUserImageFromStorage(user)
    }
}