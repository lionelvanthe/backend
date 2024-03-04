package com.example.backend.repository

import com.example.backend.models.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TeacherRepository : JpaRepository<Teacher, Int> {
    fun findById(id: String): Optional<Teacher>
    fun existsById(id: String): Boolean
}
