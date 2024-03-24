package com.example.backend.payload.response

import com.example.backend.models.Teacher
import org.springframework.format.annotation.DateTimeFormat
import java.sql.Time
import java.time.LocalTime

data class TimetableResponse(
        var title: String,
        var content: String,
        @DateTimeFormat(pattern = "HH:mm")
        val startTime: LocalTime,
        @DateTimeFormat(pattern = "HH:mm")
        val endTime: LocalTime,
        val teacher: Teacher,
)