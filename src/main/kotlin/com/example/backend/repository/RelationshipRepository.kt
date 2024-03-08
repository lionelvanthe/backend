package com.example.backend.repository

import com.example.backend.models.Relationship
import com.example.backend.models.Student
import org.springframework.data.jpa.repository.JpaRepository

interface RelationshipRepository: JpaRepository<Relationship, Int> {
    fun findAllByStudent(student: Student): List<Relationship>?
}
