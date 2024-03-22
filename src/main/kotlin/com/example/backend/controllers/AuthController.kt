package com.example.backend.controllers

import com.example.backend.models.*
import com.example.backend.models.enums.ERole
import com.example.backend.payload.request.LoginRequest
import com.example.backend.payload.request.TeacherSignupRequest
import com.example.backend.payload.response.MessageResponse
import com.example.backend.payload.response.UserInfoResponse
import com.example.backend.repository.AccountRepository
import com.example.backend.security.jwt.JwtUtils
import com.example.backend.security.services.UserDetailsImpl
import com.example.backend.service.user.StudentServiceImp
import com.example.backend.service.user.TeacherServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.naming.AuthenticationException


//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
        @Autowired
        var authenticationManager: AuthenticationManager,

        @Autowired
        var accountRepository: AccountRepository,

        @Autowired
        var studentService: StudentServiceImp,

        @Autowired
        var teacherService: TeacherServiceImp,

        @Autowired
        var encoder: PasswordEncoder,

        @Autowired
        var jwtUtils: JwtUtils) {

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest?): ResponseEntity<*> {


        return try {
            val authentication = authenticationManager
                    .authenticate(UsernamePasswordAuthenticationToken(loginRequest!!.username, loginRequest.password))
            SecurityContextHolder.getContext().authentication = authentication
            val userDetails = authentication.principal as UserDetailsImpl
            val jwtCookie = jwtUtils.generateJwtCookie(userDetails)
            val roles = userDetails.authorities.stream()
                    .map { item: GrantedAuthority? -> item!!.authority }
                    .toList()
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(UserInfoResponse(userDetails.id,
                            userDetails.username,
                            roles[0]))
        } catch (e: BadCredentialsException) {
            // Xử lý khi mật khẩu sai
            ResponseEntity.status(HttpStatus.NOT_FOUND).body<String>("Mật khẩu không đúng")
        } catch (e: AuthenticationException) {
            // Xử lý khi tên người dùng sai hoặc lỗi xác thực khác
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body<String>("Tên người dùng không tồn tại hoặc lỗi xác thực")
        }
    }

    @PostMapping("/signup/{role}")
    fun registerUser(@PathVariable role: String, @RequestBody teachSignupRequest: TeacherSignupRequest): ResponseEntity<*> {

        when (role) {
            "teacher" -> {
                if (teacherService.existsById(teachSignupRequest.id)) {
                    return ResponseEntity.badRequest().body(MessageResponse("Error: Username is already taken!"))
                }

                val account = Account(username = teachSignupRequest.id,
                        password = encoder.encode("123456"))

                account.role = ERole.ROLE_TEACHER.name

                // Create new user's account
                val teacher = teachSignupRequest.let {
                    Teacher(it.id, it.name, it.dayOfBirth, it.gender, it.address, it.email!!, it.phone!!, account)
                }
                accountRepository.save(account)
                teacherService.addUser(teacher)
            }

            "student" -> {
                if (studentService.existsById(teachSignupRequest.id)) {
                    return ResponseEntity.badRequest().body(MessageResponse("Error: Username is already taken!"))
                }

                val account = Account(username = teachSignupRequest.id,
                        password = encoder.encode("123456"))

                account.role = ERole.ROLE_STUDENT.name

                // Create new user's account
                val student = teachSignupRequest.let {
                    Student(it.id, it.name, it.dayOfBirth, it.gender, it.address, account)
                }
                accountRepository.save(account)
                studentService.addUser(student)
            }
        }

        return ResponseEntity.ok(MessageResponse("User registered successfully!"))
    }

    @PostMapping("/signout")
    fun logoutUser(): ResponseEntity<*> {
        val cookie = jwtUtils.cleanJwtCookie
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(MessageResponse("You've been signed out!"))
    }
}
