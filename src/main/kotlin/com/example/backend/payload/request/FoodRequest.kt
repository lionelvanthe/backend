package com.example.backend.payload.request

import jakarta.validation.constraints.NotBlank
import java.sql.Time

data class FoodRequest(

        var name: String,

        @NotBlank
        var ingredients: String,

        var nutrition: String,

        val urlImage: String,

        val startTime: Time,

        val endTime: Time,

        )