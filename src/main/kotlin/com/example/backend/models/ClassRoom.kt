package com.example.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*


@Entity
@Table(name = "lop")
data class ClassRoom (
        @Id
        @Column(name = "idlop")
        private val id: String,

        @Column(name = "ten")
        @NotBlank @Size(max = 45)
        private val name: String? = null,

        @Column(name = "ngay_bat_dau")
        @NotBlank
        private val startTime: Date? = null,

        @Column(name = "ngay_ket_thuc")
        @NotBlank
        private val endTime: Date? = null,

        @OneToMany(mappedBy = "classRoom")
        var  students: List<Student>? = null
)