package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        private val startTime: Date? = null,

        @Column(name = "ngay_ket_thuc")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        private val endTime: Date? = null,

        @OneToMany(mappedBy = "classRoom")
        @JsonBackReference
        var  students: List<Student>? = null
)