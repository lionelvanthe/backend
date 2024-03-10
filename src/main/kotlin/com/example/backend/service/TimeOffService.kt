package com.example.backend.service

import com.example.backend.models.Student
import com.example.backend.models.TimeOff
import com.example.backend.repository.TimeOffRepository
import org.springframework.stereotype.Service

@Service
class TimeOffService (private val timeOffRepository: TimeOffRepository) {
    fun createTimeOff(timeOff: TimeOff) {
        timeOffRepository.save(timeOff)
    }

    fun getTimeOffByStudent(student: Student): List<TimeOff>? {
        return timeOffRepository.findAllByStudent(student)
    }
}