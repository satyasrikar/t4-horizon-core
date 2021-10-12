package com.horizon.core.service

import com.horizon.core.model.User
import com.horizon.core.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class AccessServiceImpl : AccessService {
    @Autowired
    lateinit var userRepository: UserRepository


    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun getByUsername(username: String): User {
        return userRepository.findByUsername(username)
    }


}