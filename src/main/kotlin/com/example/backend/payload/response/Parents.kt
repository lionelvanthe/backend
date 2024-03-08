package com.example.backend.payload.response

import com.example.backend.models.Parent

data class Parents(
        val relationship: String,
        val parent: Parent
)