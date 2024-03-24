package com.example.backend.payload.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.*

data class TimeTableRequest(

        val time: LocalDate,

        var activities: MutableSet<ActivityRequest> = mutableSetOf()
)
