package com.example.backend.security.jwt

import com.example.backend.security.services.UserDetailsServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class AuthTokenFilter(
    private val jwtUtils: JwtUtils,
    private val userDetailsService: UserDetailsServiceImpl): OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt = parseJwt(request)
            if (jwtUtils.validateJwtToken(jwt)) {
                val username = jwtUtils.getUserNameFromJwtToken(jwt)
                val userDetails = userDetailsService.loadUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            Companion.logger.error("Cannot set user authentication: {}", e)
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String {
        return jwtUtils.getJwtFromCookies(request)!!
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AuthTokenFilter::class.java)
    }
}
