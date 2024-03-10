package com.example.backend.service

import com.example.backend.models.ClassRoom
import com.example.backend.models.Student
import com.example.backend.models.TimeOff
import com.example.backend.repository.TimeOffRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TimeOffService (private val timeOffRepository: TimeOffRepository) {
    fun createTimeOff(timeOff: TimeOff) {
        timeOffRepository.save(timeOff)
    }

    fun getTimeOffByStudent(student: Student, date: Date): List<TimeOff>? {
        return timeOffRepository.getAllByStudentAndDate(date, student)
    }

    fun findById(id: Int): Optional<TimeOff>? {
        return timeOffRepository.findById(id)
    }

    fun update(timeOff: TimeOff) {
        timeOffRepository.save(timeOff)
    }

    fun getTimeOffByDateAndClass(requestDate: Date, classRoom: ClassRoom): List<TimeOff>? {
        return timeOffRepository.getTimeOffByDateAndClass(requestDate, classRoom)
    }
}