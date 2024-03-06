package com.example.backend.repository

import com.example.backend.models.Relationship
import org.springframework.data.jpa.repository.JpaRepository

interface RelationshipRepository: JpaRepository<Relationship, Int> {

}
