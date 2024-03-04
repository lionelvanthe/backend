package com.example.backend.service.user

import com.example.backend.models.Teacher
import com.example.backend.repository.TeacherRepository
import com.example.backend.service.user.UserService
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TeacherServiceImp (private val teacherRepository: TeacherRepository): UserService<Teacher> {

    override fun getAllUser(): List<Teacher>? {
        return teacherRepository.findAll()
    }

    override fun getUser(id: String): Optional<Teacher> {
        return teacherRepository.findById(id)
    }

    override fun addUser(teacher: Teacher): Teacher {
        return teacherRepository.save(teacher)
    }

    override fun existsById(id: String): Boolean {
        return teacherRepository.existsById(id)
    }

}