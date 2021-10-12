package com.horizon.core.controller

import com.horizon.core.jwt.JwtProvider
import com.horizon.core.model.User
import com.horizon.core.repository.UserRepository
import com.horizon.core.request.LoginForm
import com.horizon.core.response.JwtResponse
import com.horizon.core.service.AccessService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthRestController {
    @Autowired
    var authenticationManager: AuthenticationManager? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var accessService: AccessService? = null

    @Autowired
    var encoder: PasswordEncoder? = null

    @Autowired
    var jwtProvider: JwtProvider? = null

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginForm?): ResponseEntity<*>? {
        val authentication: Authentication = authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest!!.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtProvider!!.generateJwtToken(authentication)

        val userDetails = authentication.getPrincipal() as UserDetails
        val data: Optional<User?>? = userRepository?.findByUsername(loginRequest.username)
        return ResponseEntity.ok(
            JwtResponse(
                data!!.get().id,
                data.get().fullName,
                data.get().organization,
                userDetails.username,
                data.get().mobile,
                data.get().isAdmin,
                jwt,
                "Bearer"
            )
        )
    }


    @GetMapping("/policyholders")
    fun getallclients(): MutableList<User?> {
        return accessService!!.getAllDetails();
    }
}