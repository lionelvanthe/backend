package com.example.backend.service

import com.example.backend.models.Height
import com.example.backend.models.Student
import com.example.backend.repository.HeightRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class HeightService(private val heightRepository: HeightRepository) {

    fun createHeight(height: Height) {
        heightRepository.save(height)
    }

    fun getById(id: Int): Height {
        return heightRepository.findById(id).get()
    }

    fun getByStudent(student: Student): List<Height> {
        return heightRepository.findAllByStudent(student)
    }

    fun updateValueHeight(id: Int, newValue: Int): Height? {
        val height = heightRepository.findById(id).get()
        height.value = newValue
        return heightRepository.save(height)
    }

    fun updateTimeHeight(id: Int, newTime: Date): Height? {
        val height = heightRepository.findById(id).get()
        height.time = newTime
        return heightRepository.save(height)
    }
}