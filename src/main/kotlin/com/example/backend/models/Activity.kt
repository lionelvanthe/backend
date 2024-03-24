package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.springframework.format.annotation.DateTimeFormat
import java.sql.Time
import java.time.LocalTime

@Entity
@Table(name = "hoat_dong")
data class Activity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idhoat_dong")
        private val id: Int = 0,

        @Column(name = "tieu_de")
        @NotBlank
        var title: String,

        @Column(name = "noi_dung")
        @NotBlank
        var content: String,

        @Column(name = "thoi_gian_bat_dau")
        @NotBlank
        @JsonFormat(pattern="KK:mm")
        val startTime: LocalTime,

        @Column(name = "thoi_gian_ket_thuc")
        @JsonFormat(pattern="KK:mm")
        val endTime: LocalTime,

        @ManyToOne
        @JoinColumn(name = "idthoi_khoa_bieu")
        @JsonBackReference
        var timetable: Timetable? = null,

        @ManyToOne
        @JoinColumn(name = "idgiao_vien")
        @JsonBackReference
        var teacher: Teacher ?= null,

)