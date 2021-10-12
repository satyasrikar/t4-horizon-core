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

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val user = o as UserPrincipal
        return Objects.equals(id, user.id)
    }


    companion object {
        private const val serialVersionUID = 1L
        @JvmStatic
        fun build(user: User?): UserPrincipal {
            return UserPrincipal(
                user?.id.toString(),
                user?.fullName.toString(),
                user?.username.toString(),
                user?.password.toString()


            )
        }
    }
}