package com.example.backend.payload.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.Date

class StudentSignupRequest(
    @NotBlank
    var id: String,

    var dayOfBirth: Date,

    @NotBlank
    var address: String,

    @NotBlank
    var name: String,

    @NotBlank
    var gender: String,
)
