package com.example.backend.repository

import com.example.backend.models.Student
import com.example.backend.models.Weight
import org.springframework.data.jpa.repository.JpaRepository

interface WeightRepository: JpaRepository<Weight, Int> {

    fun findAllByStudent(student: Student): List<Weight>
}