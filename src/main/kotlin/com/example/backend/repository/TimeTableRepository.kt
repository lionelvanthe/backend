package com.example.backend.repository

import com.example.backend.models.Activity
import com.example.backend.models.ClassRoom
import com.example.backend.models.Food
import com.example.backend.models.Timetable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface TimeTableRepository: JpaRepository<Timetable, Int> {
    fun findByTime(time: Date): Optional<Timetable>

    @Query("SELECT s.activities FROM Timetable s WHERE s.time = :time")
    fun findActivitiesByTime(@Param("time") time: Date): Optional<List<Activity>>

    fun findByClassRoomAndTime(classRoom: ClassRoom, time: Date): Optional<Timetable>
}
