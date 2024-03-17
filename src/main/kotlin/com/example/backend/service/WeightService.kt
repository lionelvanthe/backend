package com.example.backend.service

import com.example.backend.models.Height
import com.example.backend.models.Weight
import com.example.backend.models.Student
import com.example.backend.repository.WeightRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WeightService(private val weightRepository: WeightRepository) {

    fun createWeight(weight: Weight) {
        weightRepository.save(weight)
    }

    fun getById(id: Int): Weight {
        return weightRepository.findById(id).get()
    }


    fun getByStudent(student: Student): List<Weight> {
        return weightRepository.findAllByStudent(student)
    }

    fun updateValueWeight(id: Int, newValue: Int): Weight? {
        val weight = weightRepository.findById(id).get()
        weight.value = newValue
        return weightRepository.save(weight)
    }

    fun updateTimeWeight(id: Int, newTime: Date): Weight? {
        val weight = weightRepository.findById(id).get()
        weight.time = newTime
        return weightRepository.save(weight)
    }
}