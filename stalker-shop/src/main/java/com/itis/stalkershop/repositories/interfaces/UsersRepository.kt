package com.itis.stalkershop.repositories.interfaces;

import com.itis.stalkershop.models.User
import com.itis.stalkershop.models.UserDto
import java.util.*

interface UsersRepository: Repository<String, UserDto, User>  {
    fun findByEmail(email: String): Optional<User>
}
