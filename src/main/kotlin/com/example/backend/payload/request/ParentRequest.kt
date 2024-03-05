package com.example.backend.payload.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.Date

class ParentRequest(

    var dayOfBirth: Date,

    @NotBlank
    var phone: String,

    @NotBlank
    var name: String,

    @NotBlank
    var relationShip: String,

    @NotBlank
    @Email
    var email: String? = null
)
