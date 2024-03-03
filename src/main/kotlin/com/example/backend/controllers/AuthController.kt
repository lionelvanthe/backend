package com.example.backend.controllers

import com.example.backend.models.User
import com.example.backend.payload.request.LoginRequest
import com.example.backend.payload.request.SignupRequest
import com.example.backend.payload.response.MessageResponse
import com.example.backend.payload.response.UserInfoResponse
import com.example.backend.repository.UserRepository
import com.example.backend.security.jwt.JwtUtils
import com.example.backend.security.services.UserDetailsImpl
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
        @Autowired
        var authenticationManager: AuthenticationManager,

        @Autowired
        var userRepository: UserRepository,

        @Autowired
        var encoder: PasswordEncoder,

        @Autowired
        var jwtUtils: JwtUtils) {

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest?): ResponseEntity<*> {
        val authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(loginRequest!!.username, loginRequest.password))
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails = authentication.principal as UserDetailsImpl
        val jwtCookie = jwtUtils!!.generateJwtCookie(userDetails)
        val roles = userDetails.authorities.stream()
                .map { item: GrantedAuthority? -> item!!.authority }
                .toList()
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(UserInfoResponse(userDetails.id,
                        userDetails.username,
                        roles[0]))
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: SignupRequest): ResponseEntity<*> {
        println("vo day")
        println(signUpRequest.username)
        println(signUpRequest.password)
        println(signUpRequest.role)
        if (userRepository!!.existsByUsername(signUpRequest.username) == true) {
            return ResponseEntity.badRequest().body(MessageResponse("Error: Username is already taken!"))
        }


        // Create new user's account
        val user = User(username = signUpRequest.username,
                password = encoder!!.encode(signUpRequest.password))
        val strRole = signUpRequest.role
        user.role = strRole
        userRepository!!.save(user)
        return ResponseEntity.ok(MessageResponse("User registered successfully!"))
    }

    @PostMapping("/signout")
    fun logoutUser(): ResponseEntity<*> {
        val cookie = jwtUtils!!.cleanJwtCookie
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(MessageResponse("You've been signed out!"))
    }
}
