package com.example.backend.payload.request

import java.sql.Time

data class ActivityRequest(

        var title: String,
        var content: String,

        val startTime: Time,

        val endTime: Time,

        var idTeacher: String,
)

