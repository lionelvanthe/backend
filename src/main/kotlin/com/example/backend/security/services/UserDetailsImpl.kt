package com.example.backend.security.services

import com.example.backend.models.Account
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val id: Long, private val username: String, @field:JsonIgnore private val password: String,
                      private val authorities: Collection<GrantedAuthority>) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
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
        val user = o as UserDetailsImpl
        return id == user.id
    }

    companion object {
        private const val serialVersionUID = 1L
        @JvmStatic
        fun build(account: Account): UserDetailsImpl {
            val authorities: MutableList<GrantedAuthority> = ArrayList()
            authorities.add(SimpleGrantedAuthority(account.role))
            return UserDetailsImpl(
                    account.id.toLong(),
                    account.username,
                    account.password,
                    authorities)
        }
    }
}
