package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.sql.Time

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
        val startTime: Time,

        @Column(name = "thoi_gian_ket_thuc")
        val endTime: Time,

        @ManyToOne
        @JoinColumn(name = "idthoi_khoa_bieu")
        @JsonBackReference
        var timetable: Timetable? = null,

        @ManyToOne
        @JoinColumn(name = "idgiao_vien")
        @JsonBackReference
        var teacher: Teacher ?= null,

)