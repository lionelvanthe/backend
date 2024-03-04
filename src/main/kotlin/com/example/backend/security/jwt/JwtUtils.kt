package com.example.backend.security.jwt

import com.example.backend.security.services.UserDetailsImpl
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Scope
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils
import java.security.Key
import java.util.*

@Component
@Scope("prototype")
class JwtUtils {
    @Value("\${bezkoder.app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${bezkoder.app.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    @Value("\${bezkoder.app.jwtCookieName}")
    private val jwtCookie: String? = null
    fun getJwtFromCookies(request: HttpServletRequest?): String? {
        val cookie = WebUtils.getCookie(request!!, jwtCookie!!)
        println("thenv: ${cookie}, ${cookie?.value}")
        return cookie?.value
    }

    fun generateJwtCookie(userPrincipal: UserDetailsImpl): ResponseCookie {
        val jwt = generateTokenFromUsername(userPrincipal.username)
        return ResponseCookie.from(jwtCookie!!, jwt).path("/api").maxAge((24 * 60 * 60).toLong()).httpOnly(true).build()
    }

    val cleanJwtCookie: ResponseCookie
        get() = ResponseCookie.from(jwtCookie!!, "").path("/api").build()

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts.parser().setSigningKey(key()).build()
                .parseClaimsJws(token).body.subject
    }

    private fun key(): Key {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(authToken)
            return true
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    fun generateTokenFromUsername(username: String?): String {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}
