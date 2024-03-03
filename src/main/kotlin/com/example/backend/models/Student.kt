package com.example.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

@Entity
@Table(name = "hoc_sinh")
class Student (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idhoc_sinh")
        private val id: Int? = null,

        @Column(name = "ho_ten")
        @NotBlank @Size(max = 255)
        private val name: String? = null,

        @Column(name = "ngay_sinh")
        @NotBlank
        private val dayOfBirth: Date? = null,

        @Column(name = "gioi_tinh")
        @NotBlank @Size(max = 45)
        private val gender: String? = null,

        @Column(name = "dai_chi")
        @NotBlank @Size(max = 255)
        private val address: String? = null
)
