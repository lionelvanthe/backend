package com.example.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*
import kotlin.collections.HashSet

@Entity
@Table(name = "phu_huynh")
data class Parent (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idphu_huynh")
        private val id: Int = 0,

        @Column(name = "ho_ten")
        @NotBlank @Size(max = 255)
        private val name: String? = null,

        @Column(name = "ngay_sinh")
        @NotBlank
        private val dayOfBirth: Date? = null,

        @Column(name = "email")
        @NotBlank @Size(max = 255)
        @Email
        private val email: String? = null,

        @Column(name = "so_dien_thoai")
        @NotBlank @Size(max = 255)
        private val phoneNumber:  String,

)
