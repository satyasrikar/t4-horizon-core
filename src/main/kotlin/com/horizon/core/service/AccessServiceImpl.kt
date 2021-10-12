package com.horizon.core.service

import com.horizon.core.model.User
import com.horizon.core.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class AccessServiceImpl : AccessService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    var encoder: PasswordEncoder? = null

    override fun getAllDetails(): MutableList<User?> {
        return userRepository!!.findAll()
    }

    override fun getByUsername(username: String?): Optional<User?>? {
        return userRepository?.findByUsername(username)
    }

    override fun existByUsername(emailId: String?): Boolean? {
        return userRepository?.existsByUsername(emailId)
    }

    override fun existUserByMobileNumber(mobile: String?): Boolean? {
        return userRepository?.existsByMobile(mobile)
    }

    override fun findByMobile(mobile: String?): User? {
        return userRepository?.findByMobile(mobile)
    }

}