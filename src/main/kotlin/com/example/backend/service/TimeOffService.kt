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

    fun getTimeOffByStudentAndDate(student: Student, date: Date): Optional<List<TimeOff>> {
        return timeOffRepository.getAllByStudentAndDate(date, student)
    }

    fun getTimeOffByStudentAndDate(student: Student, startTime: Date, endTime: Date): Optional<List<TimeOff>> {
        return timeOffRepository.getAllByStudentAndDate(startTime, endTime, student)
    }

    fun getTimeOffByStudent(student: Student): Optional<List<TimeOff>> {
        return timeOffRepository.getAllByStudent(student)
    }

    fun findById(id: Int): Optional<TimeOff>? {
        return timeOffRepository.findById(id)
    }

    fun update(timeOff: TimeOff) {
        timeOffRepository.save(timeOff)
    }

    fun getTimeOffByDateAndClass(requestDate: Date, classRoom: ClassRoom): Optional<List<TimeOff>> {
        return timeOffRepository.getTimeOffByDateAndClass(requestDate, classRoom)
    }
}