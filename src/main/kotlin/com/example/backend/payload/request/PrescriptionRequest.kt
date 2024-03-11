package com.example.backend.payload.request

import com.example.backend.models.Medicine
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotBlank
import java.util.*

data class PrescriptionRequest(
        @NotBlank
        var pathological: String,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        var startTime: Date,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        var endTime: Date,


        var medicines: List<MedicineRequest>
)