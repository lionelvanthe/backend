package com.example.backend.repository

import com.example.backend.models.Student
import com.example.backend.models.TimeOff
import org.springframework.data.jpa.repository.JpaRepository

interface TimeOffRepository: JpaRepository<TimeOff, Int> {
    fun findAllByStudent(student: Student): List<TimeOff>?
}