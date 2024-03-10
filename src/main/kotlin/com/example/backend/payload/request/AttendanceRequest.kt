package com.example.backend.payload.request

import jakarta.validation.constraints.NotBlank
import java.util.*

data class AttendanceRequest(
        @NotBlank
        var idStudent: String,
        @NotBlank
        var state: String
)