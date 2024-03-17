package com.example.backend.repository

import com.example.backend.models.Height
import com.example.backend.models.Student
import org.springframework.data.jpa.repository.JpaRepository

interface HeightRepository: JpaRepository<Height, Int> {

    fun findAllByStudent(student: Student): List<Height>
}