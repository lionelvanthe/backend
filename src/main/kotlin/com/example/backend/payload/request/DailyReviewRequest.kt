package com.example.backend.payload.request

import com.example.backend.models.Student
import com.example.backend.models.Teacher
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.util.*

data class DailyReviewRequest(
        @NotBlank
        val studyReview: String,

        @NotBlank
        val activityReview: String,

        @NotBlank
        val otherReview: String,

        @Column(name = "ngày_nhan_xet")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        val timeReview: Date,

        @Column(name = "url_phieu_be_ngoan")
        @NotBlank
        val url: String? = null
)
