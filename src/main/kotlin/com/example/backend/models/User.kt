package com.example.backend.models

import java.util.*

abstract class User {
    abstract val id: String

    abstract val name: String

    abstract val dayOfBirth: Date

    abstract val gender: String

    abstract val address: String
}