package com.horizon.core.service

import com.horizon.core.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
interface AccessService {
    fun getByUsername(username: String): User

    fun getAllUsers() : List<User>

}