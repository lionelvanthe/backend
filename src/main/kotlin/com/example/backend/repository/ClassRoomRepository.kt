package com.example.backend.repository

import com.example.backend.models.ClassRoom
import com.example.backend.models.Relationship
import org.springframework.data.jpa.repository.JpaRepository

interface ClassRoomRepository: JpaRepository<ClassRoom, String> {

}
