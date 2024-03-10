package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonFormat
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
        val id: Int = 0,

        @Column(name = "ho_ten")
        @NotBlank @Size(max = 255)
        val name: String,

        @Column(name = "ngay_sinh")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        val dayOfBirth: Date,

        @Column(name = "email")
        @NotBlank @Size(max = 255)
        @Email
        val email: String? = null,

        @Column(name = "so_dien_thoai")
        @NotBlank @Size(max = 255)
        val phoneNumber:  String,


)
