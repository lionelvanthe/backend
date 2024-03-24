package com.example.backend.service

import com.example.backend.models.ClassRoom
import com.example.backend.models.DailyMenu
import com.example.backend.models.Timetable
import com.example.backend.repository.TimeTableRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class TimeTableService (private val timeTableRepository: TimeTableRepository){
    fun createTimeTable(timetable: Timetable) {
        timeTableRepository.save(timetable)
    }

    fun getByTime(time: LocalDate): Optional<Timetable> {
        return timeTableRepository.findByTime(time)
    }

    fun getByClassRoomAndTime(classRoom: ClassRoom, time: LocalDate): Optional<Timetable> {
        return timeTableRepository.findByClassRoomAndTime(classRoom, time)
    }

}