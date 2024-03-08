package com.example.backend.service.user

import com.example.backend.models.ClassRoom
import com.example.backend.models.Student
import com.example.backend.repository.StudentRepository
import com.example.backend.service.user.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentServiceImp(private val studentRepository: StudentRepository): UserService<Student> {
    override fun getAllUser(): List<Student>? {
        return studentRepository.findAll()
    }

    override fun getUser(id: String): Optional<Student> {
        return studentRepository.findById(id)
    }

    override fun existsById(id: String): Boolean {
        return studentRepository.existsById(id)
    }

    override fun addUser(student: Student): Student {
        return studentRepository.save(student)
    }

    fun findStudentByClass(classRoom: ClassRoom): List<Student> {
        return studentRepository.findAllByClassRoom(classRoom)
    }

}