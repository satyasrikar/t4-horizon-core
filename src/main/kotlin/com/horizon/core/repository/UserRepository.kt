package com.horizon.core.repository

import com.horizon.core.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*


interface UserRepository : MongoRepository<User?, String?> {
    fun findByUsername(username: String?): Optional<User?>?
    fun existsByUsername(username: String?): Boolean?
    fun findByMobile(mobile: String?): User?
    fun existsByMobile(mobile: String?): Boolean?
}