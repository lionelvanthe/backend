package com.example.backend.repository

import com.example.backend.models.Attendance
import com.example.backend.models.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.Repository
import java.util.*


interface AttendanceRepository: JpaRepository<Attendance, Int> {

    fun findAllByStudentAndTimeBetweenAndStateNot(student: Student, startTime: Date, endTime: Date, state: String): List<Attendance>?
}