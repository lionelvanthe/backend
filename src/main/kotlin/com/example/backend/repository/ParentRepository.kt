package com.example.backend.repository

import com.example.backend.models.Parent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ParentRepository : JpaRepository<Parent, Int> {
    fun findByNameAndPhoneNumber(name: String, phone: String): Parent?
}
