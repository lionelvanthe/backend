package com.example.backend.controllers

import com.example.backend.models.*
import com.example.backend.payload.request.LoginRequest
import com.example.backend.payload.request.TeacherSignupRequest
import com.example.backend.payload.request.TimeOffRequest
import com.example.backend.payload.response.MessageResponse
import com.example.backend.payload.response.UserInfoResponse
import com.example.backend.repository.TeacherRepository
import com.example.backend.repository.AccountRepository
import com.example.backend.security.jwt.JwtUtils
import com.example.backend.security.services.UserDetailsImpl
import com.example.backend.service.ClassRoomService
import com.example.backend.service.TimeOffService
import com.example.backend.service.user.StudentServiceImp
import com.example.backend.service.user.TeacherServiceImp
import com.example.backend.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.sql.Time
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/timeoff")
class TimeOffController(
        private val timeOffService: TimeOffService,
        private val classRoomService: ClassRoomService,
        private val studentServiceImp: StudentServiceImp) {

    @PostMapping("/{idStudent}/create")
    fun createTimeOff(
            @PathVariable idStudent: String,
            @RequestBody timeOffRequest: TimeOffRequest): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent).get()
        timeOffRequest.let {
            val timeOff = TimeOff(
                    startTime = it.startTime,
                    endTime = it.endTime,
                    reason = it.reason,
                    note = it.note,
                    student = student)

            timeOffService.createTimeOff(timeOff)
            return ResponseEntity.ok(timeOff)
        }
    }

    @GetMapping("/{idStudent}/get_by_student")
    fun getTimeOffByStudent(@PathVariable idStudent: String,
                            @RequestParam (name = "time") time: LocalDate): ResponseEntity<*> {
        val student = studentServiceImp.getUser(idStudent).get()

        val timeOffs = timeOffService.getTimeOffByStudent(student,
                Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant()))

        return if (timeOffs != null) {
            ResponseEntity.ok(timeOffs)
        } else {
            ResponseEntity.ok("nothing")
        }
    }
    @GetMapping("/{idClass}/get_by_class")
    fun getTimeOffByDateAndClass(
            @PathVariable idClass: String,
            @RequestParam (name = "time") time: LocalDate): ResponseEntity<*> {
        val classRoom = classRoomService.findById(idClass)?.get() ?: return ResponseEntity.ok("try again")

        val timeOffs = timeOffService.getTimeOffByDateAndClass(
                Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                classRoom)

        return if (timeOffs != null) {
            ResponseEntity.ok(timeOffs)
        } else {
            ResponseEntity.ok("nothing")
        }
    }

    @PostMapping("/{idTeacher}")
    fun confirmTimeOff(@PathVariable idTeacher: String,
                       @RequestParam (name = "idTimeOff") idTimeOff: Int,
                       @RequestParam (name = "state") state: String): ResponseEntity<*> {

        val timeOff = timeOffService.findById(idTimeOff)?.get() ?: return ResponseEntity.ok("nothing")
        timeOff.state = state
        timeOff.idTeacher  = idTeacher

        timeOffService.update(timeOff)

        return ResponseEntity.ok("successfully")
    }
}
