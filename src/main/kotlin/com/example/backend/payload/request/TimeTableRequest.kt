package com.example.backend.payload.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class TimeTableRequest(

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        val time: Date,

        var activities: MutableSet<ActivityRequest> = mutableSetOf()
)
