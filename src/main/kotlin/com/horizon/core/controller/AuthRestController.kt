package com.horizon.core.controller

import com.horizon.core.jwt.JwtProvider
import com.horizon.core.model.User
import com.horizon.core.repository.UserRepository
import com.horizon.core.request.LoginForm
import com.horizon.core.response.CustomJWTResponse
import com.horizon.core.response.JwtResponse
import com.horizon.core.service.AccessService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthRestController {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var jwtProvider: JwtProvider

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: LoginForm): ResponseEntity<*>? {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication

        //Generate JSONWebToken from Authentication Object
        val jwt: String = jwtProvider.generateJwtToken(authentication)

        val userDetails = authentication.principal as UserDetails
        val userData: User = userRepository.findByUsername(loginRequest.username)
        val jwtResponse = JwtResponse()
        jwtResponse.username = userDetails.username
        jwtResponse.type = "Bearer"
        jwtResponse.id = userData.id
        jwtResponse.fullName = userData.fullName
        jwtResponse.organization = userData.organization
        jwtResponse.mobile = userData.mobile
        jwtResponse.isAdmin = userData.isAdmin
        jwtResponse.token = jwt

        val customResponse = CustomJWTResponse()
        customResponse.type = "Bearer"
        customResponse.token = jwt
        customResponse.username = userData.username
        customResponse.name = userData.fullName
        
        return ResponseEntity.ok(
            customResponse
        )
    }

}