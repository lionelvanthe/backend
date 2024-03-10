package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*


@Entity
@Table(name = "attendance")
data class Attendance(
        @Id
        @Column(name = "idattendance")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Int = 0,

        @Column(name = "thoi_gian")
        private val time: Date? = null,

        @Column(name = "trang_thai")
        @NotBlank
        private val state: String? = null,

        @ManyToOne
        @JoinColumn(name = "idhoc_sinh")
        @JsonBackReference
        var student: Student ? = null,

        @ManyToOne
        @JoinColumn(name = "idgiao_vien")
        @JsonBackReference
        var teacher: Teacher ? = null,

)
