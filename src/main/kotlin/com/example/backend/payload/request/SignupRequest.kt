package com.example.backend.payload.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class SignupRequest(
    @NotBlank @Size(min = 3, max = 20)
    var username: String,
    @NotBlank
    var role:String,
    @NotBlank @Size(min = 6, max = 40)
    var password: String
)
