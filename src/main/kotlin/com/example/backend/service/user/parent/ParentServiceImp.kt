package com.example.backend.service.user.parent

import com.example.backend.models.Parent
import com.example.backend.repository.ParentRepository
import org.springframework.stereotype.Service

@Service
class ParentServiceImp(private val parentRepository: ParentRepository): ParentService {
    override fun addParent(parent: Parent): Parent {
        return parentRepository.save(parent)
    }

    override fun getParentByNameAndPhone(name: String, phone: String): Parent? {
        return parentRepository.findByNameAndPhoneNumber(name, phone)
    }
}