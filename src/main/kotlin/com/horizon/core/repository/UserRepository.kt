package com.horizon.core.repository

import com.horizon.core.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*


interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User
}