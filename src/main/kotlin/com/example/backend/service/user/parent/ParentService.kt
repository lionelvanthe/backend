package com.example.backend.service.user.parent

import com.example.backend.models.Parent
import org.springframework.stereotype.Service

@Service
interface ParentService {
    fun addParent(parent: Parent): Parent
    fun getParentByNameAndPhone(name: String, phone: String): Parent?
}