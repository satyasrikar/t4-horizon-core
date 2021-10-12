package com.horizon.core.jwt

import com.horizon.core.service.UserPrincipal
import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtProvider {

    private val jwtSecret: String = "horizonSecret"

    private val jwtExpiration = 86400
    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpiration * 1000))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            log.error("Invalid JWT signature -> Message: {} ", e)
        } catch (e: MalformedJwtException) {
            log.error("Invalid JWT token -> Message: {}", e)
        } catch (e: ExpiredJwtException) {
            log.error("Expired JWT token -> Message: {}", e)
        } catch (e: UnsupportedJwtException) {
            log.error("Unsupported JWT token -> Message: {}", e)
        } catch (e: IllegalArgumentException) {
            log.error("JWT claims string is empty -> Message: {}", e)
        }
        return false
    }

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body.subject
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JwtProvider::class.java)
    }
}