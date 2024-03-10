package com.example.backend.repository

import com.example.backend.models.ClassRoom
import com.example.backend.models.Student
import com.example.backend.models.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StudentRepository : JpaRepository<Student, Int> {
    fun findById(id: String): Optional<Student>
    fun existsById(id: String): Boolean
    fun findAllByClassRoom(classRoom: ClassRoom): List<Student>

}
