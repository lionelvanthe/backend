package com.example.backend.payload.request

import jakarta.validation.constraints.NotBlank
import java.util.*

data class ClassRoomRequest(
        @NotBlank
        var id: String,
        @NotBlank
        var name: String,
        var startTime: Date,
        var endTime: Date,
)
