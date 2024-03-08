package com.example.backend.service

import com.example.backend.models.ClassRoom
import com.example.backend.models.Parent
import com.example.backend.models.Relationship
import com.example.backend.models.Student
import com.example.backend.repository.ClassRoomRepository
import com.example.backend.repository.RelationshipRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClassRoomService(private val classRoomRepository: ClassRoomRepository) {

    fun addClassRoom(classRoom: ClassRoom) {
        classRoomRepository.save(classRoom)
    }

    fun findById(id: String): Optional<ClassRoom>? {
        return classRoomRepository.findById(id)
    }
}