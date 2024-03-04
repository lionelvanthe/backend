package com.example.backend.service.user

import com.example.backend.models.User
import org.springframework.stereotype.Service
import java.util.Optional

@Service
interface UserService <T: User> {

    //Get all teachers
    fun getAllUser(): List<T>?

    //Get teacher by id
    fun getUser(id: String): Optional<T>

    //Create new teacher
    fun addUser(teacher: T): T

    fun existsById(id: String): Boolean

}