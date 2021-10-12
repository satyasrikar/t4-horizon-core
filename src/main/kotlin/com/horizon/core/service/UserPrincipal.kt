package com.horizon.core.service

import com.fasterxml.jackson.annotation.JsonIgnore
import com.horizon.core.model.User
import org.springframework.security.core.GrantedAuthority


import org.springframework.security.core.userdetails.UserDetails
import java.util.*


class UserPrincipal(
    private val id: String, val name: String,
    private val username: String, @field:JsonIgnore private val password: String
) : UserDetails {


    private val authorities: Collection<GrantedAuthority?>? = null
    override fun getUsername(): String {
        return username
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return authorities
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val user = other as UserPrincipal
        return Objects.equals(id, user.id)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + (authorities?.hashCode() ?: 0)
        return result
    }


    companion object {
        private const val serialVersionUID = 1L
        @JvmStatic
        fun build(user: User): UserPrincipal {
            return UserPrincipal(user.id, user.fullName, user.username, user.password)
        }
    }
}