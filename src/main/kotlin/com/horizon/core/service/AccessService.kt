package com.horizon.core.service

import com.horizon.core.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
interface AccessService {
    fun getByUsername(username: String?): Optional<User?>?
    fun existByUsername(emailId: String?): Boolean?
    fun existUserByMobileNumber(mobile: String?): Boolean?
    fun findByMobile(mobile: String?): User?

    fun getAllDetails() : MutableList<User?>
//
//    @Throws(Exception::class)
//    fun resetPasswordById(id: String?, passwordResetRequest: PasswordResetRequest?): String?
//
//    @Throws(Exception::class)
//    fun setUpPassword(id: String?, setupPassword: SetUpPasswordRequest?): String?
}