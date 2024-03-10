package com.example.backend.repository

import com.example.backend.models.Attendance
import com.example.backend.models.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.Repository


interface AttendanceRepository: JpaRepository<Attendance, Int> {

    fun findAllByStudent(student: Student): List<Attendance>?
}