package com.example.backend.service

import com.example.backend.models.Attendance
import com.example.backend.models.enums.EStateAttendance
import com.example.backend.models.Student
import com.example.backend.repository.AttendanceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AttendanceService(private val attendanceRepository: AttendanceRepository) {

    fun addAttendance(attendance: Attendance) {
        attendanceRepository.save(attendance)
    }

    fun getAttendanceByStudent(student: Student,
                               startTime: Date,
                               endTime: Date,
                               state: String = EStateAttendance.ATTENDING_CLASS.name
    ): List<Attendance>? {
        return attendanceRepository.findAllByStudentAndTimeBetweenAndStateNot(student, startTime, endTime, state)
    }
}