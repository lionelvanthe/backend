package com.example.backend.payload.request

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
        @NotBlank
        var username: String,
        @NotBlank
        var password:String
)
