package com.example.backend.controllers

import com.example.backend.models.Attendance
import com.example.backend.models.EStateAttendance
import com.example.backend.payload.request.AttendanceRequest
import com.example.backend.payload.response.MessageResponse
import com.example.backend.payload.response.Parents
import com.example.backend.service.AttendanceService
import com.example.backend.service.RelationshipService
import com.example.backend.service.user.StudentServiceImp
import com.example.backend.service.user.TeacherServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/attendance")
class AttendanceController(
        private val studentServiceImp: StudentServiceImp,
        private val teacherServiceImp: TeacherServiceImp,
        private val attendanceService: AttendanceService) {

    @PostMapping("/{idTeacher}/rollcall")
    fun addInfoParent(@PathVariable idTeacher: String,
                      @RequestBody attendanceRequests: List<AttendanceRequest>): ResponseEntity<*> {

        val teacher = teacherServiceImp.getUser(idTeacher).get()
        val classRoom = studentServiceImp.getUser(attendanceRequests[0].idStudent).get().classRoom

        val studentsInClass = classRoom?.students?.toMutableList()

        attendanceRequests.forEach {
            val stu = studentServiceImp.getUser(it.idStudent).get()
            if (it.state == EStateAttendance.EXCUSED_ABSENCE.name || it.state == EStateAttendance.UNEXCUSED_ABSENCE.name) {
                studentsInClass?.remove(stu)
            }
            val state = it.state
            val attendance = Attendance(time = Date(), state = state, student = stu, teacher = teacher)
            attendanceService.addAttendance(attendance)
        }

        studentsInClass?.forEach {
            val state = EStateAttendance.ATTENDING_CLASS.name
            val attendance = Attendance(time = Date(), state = state, student = it, teacher = teacher)
            attendanceService.addAttendance(attendance)
        }

        return ResponseEntity.ok(MessageResponse("roll call successfully!"))

    }
}