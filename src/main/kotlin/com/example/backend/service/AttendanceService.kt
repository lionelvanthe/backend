package com.example.backend.service

import com.example.backend.models.Attendance
import com.example.backend.models.Student
import com.example.backend.repository.AttendanceRepository
import org.springframework.stereotype.Service

@Service
class AttendanceService(private val attendanceRepository: AttendanceRepository) {

    fun addAttendance(attendance: Attendance) {
        attendanceRepository.save(attendance)
    }

    fun getAttendanceByStudent(student: Student): List<Attendance>? {
        return attendanceRepository.findAllByStudent(student)
    }
}