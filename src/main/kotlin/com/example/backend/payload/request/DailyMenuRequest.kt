package com.example.backend.payload.request

import com.example.backend.models.ClassRoom
import com.example.backend.models.Food
import com.example.backend.models.Student
import com.example.backend.models.Teacher
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.util.*

data class DailyMenuRequest(

        @Column(name = "ngày")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        val time: Date,

        var foods: MutableSet<FoodRequest> = mutableSetOf()
)
