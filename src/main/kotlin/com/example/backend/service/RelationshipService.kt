package com.example.backend.service

import com.example.backend.models.Relationship
import com.example.backend.models.Student
import com.example.backend.repository.RelationshipRepository
import org.springframework.stereotype.Service

@Service
class RelationshipService(private val relationshipRepository: RelationshipRepository) {

    fun addRelationship(relationship: Relationship) {
        relationshipRepository.save(relationship)
    }

    fun getRelationshipByStudent(student: Student): List<Relationship>? {
        return relationshipRepository.findAllByStudent(student)
    }
}