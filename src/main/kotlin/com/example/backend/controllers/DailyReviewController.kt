package com.example.backend.controllers

import com.example.backend.models.*
import com.example.backend.payload.request.DailyReviewRequest
import com.example.backend.service.DailyReviewService
import com.example.backend.service.user.StudentServiceImp
import com.example.backend.service.user.TeacherServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/dailyreview")
class DailyReviewController(
        private val dailyReviewService: DailyReviewService,
        private val studentServiceImp: StudentServiceImp,
        private val teacherServiceImp: TeacherServiceImp) {

    @PostMapping("/{idTeacher}/create")
    fun createDailyReview(
            @PathVariable idTeacher: String,
            @RequestParam (name = "idstudent") idStudent: String,
            @RequestBody dailyReviewRequest: DailyReviewRequest): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent)
        val teacher = teacherServiceImp.getUser(idTeacher)

        if (student.isPresent) {

            dailyReviewRequest.let {
                val dailyReview = DailyReview(
                        studyReview = it.studyReview,
                        activityReview = it.activityReview,
                        otherReview = it.otherReview,
                        timeReview = it.timeReview,
                        url = "it.url",
                        student = student.get(),
                        teacher = teacher.get())

                dailyReviewService.createDailyReview(dailyReview)
            }
            return ResponseEntity.ok("creat daily review successfully")

        } else {
            return ResponseEntity.ok("student not found")

        }
    }

    @GetMapping("/{idStudent}/get_by_student")
    fun getDailyReviewByStudent(@PathVariable idStudent: String,
                            @RequestParam (name = "time") time: LocalDate): ResponseEntity<*> {
        val student = studentServiceImp.getUser(idStudent).get()

        val dailyReview = dailyReviewService.getDailyReviewByStudentAndTime(student,
                Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant()))

        return if (dailyReview.isPresent) {
            ResponseEntity.ok(dailyReview)
        } else {
            ResponseEntity.ok("daily review not found")
        }
    }

}
