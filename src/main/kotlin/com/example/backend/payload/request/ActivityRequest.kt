package com.example.backend.payload.request

import org.springframework.format.annotation.DateTimeFormat
import java.sql.Time
import java.time.LocalTime

data class ActivityRequest(

        var title: String,
        var content: String,

        @DateTimeFormat(pattern = "HH:mm")
        val startTime: LocalTime,

        @DateTimeFormat(pattern = "HH:mm")
        val endTime: LocalTime,

        var idTeacher: String,
)

