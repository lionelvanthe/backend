package com.example.backend.payload.request

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotBlank
import java.util.*

data class MedicineRequest(
        @NotBlank
        var name: String,

        @NotBlank
        var guide: String,

        var note: String? = null
)