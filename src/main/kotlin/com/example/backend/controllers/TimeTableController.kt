package com.example.backend.controllers

import com.example.backend.models.Activity
import com.example.backend.models.Timetable
import com.example.backend.payload.request.TimeTableRequest
import com.example.backend.service.*
import com.example.backend.service.user.StudentServiceImp
import com.example.backend.service.user.TeacherServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/timetable")
class TimeTableController(
        private val classRoomService: ClassRoomService,
        private val timeTableService: TimeTableService,
        private val activityService: ActivityService,
        private val studentService: StudentServiceImp,
        private val teacherServiceImp: TeacherServiceImp
) {

    @PostMapping("/{idClass}/create")
    fun createDailyMenu(
            @PathVariable idClass: String,
            @RequestBody timeTableRequest: TimeTableRequest): ResponseEntity<*> {

        val classRoom = classRoomService.findById(idClass)

        if (classRoom.isPresent) {
            timeTableRequest.let {
                val timeTable = Timetable(
                        time = it.time,
                        classRoom = classRoom.get()
                )

                timeTableService.createTimeTable(timeTable)

                val activities = ArrayList<Activity>()
                it.activities.map { ac ->
                    val teacher = teacherServiceImp.getUser(ac.idTeacher).get()
                    val activity = Activity(title = ac.title, content = ac.content, startTime = ac.startTime, endTime = ac.endTime, teacher = teacher, timetable = timeTable)
                    activities.add(activity)
                    activityService.createActivity(activity)
                }

            }

            return ResponseEntity.ok("create menu successfully")

        } else {
            return ResponseEntity.ok("class not found")
        }

    }

    @GetMapping("/{idStudent}/get")
    fun getDailyMenuByStudent(
            @PathVariable idStudent: String,
            @RequestParam(name = "time") time: LocalDate): ResponseEntity<*> {

        val student = studentService.getUser(idStudent).get()

        val classRoom = student.classRoom

        val timeTable = timeTableService.getByClassRoomAndTime(classRoom!!,
                Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant()))

        return if (timeTable.isPresent) {
            val activities = timeTable.get().activities

            ResponseEntity.ok(activities)
        } else {
            ResponseEntity.ok("time table not found")

        }

    }
}