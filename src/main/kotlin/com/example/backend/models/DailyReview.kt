package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "nhan_xet")
data class DailyReview (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idnhan_xet")
        val id: Int = 0,

        @Column(name = "nx_hoc_tap")
        @NotBlank
        val studyReview: String,

        @Column(name = "nx_hoat_dong")
        @NotBlank
        val activityReview: String,

        @Column(name = "nx_khac")
        @NotBlank
        val otherReview: String,

        @Column(name = "ngày_nhan_xet")
        @NotBlank
        val timeReview: LocalDate,

        @Column(name = "url_phieu_be_ngoan")
        @NotBlank
        val url: String,

        @ManyToOne
        @JoinColumn(name = "idhoc_sinh")
        @JsonBackReference
        var student: Student ? = null,

        @ManyToOne
        @JoinColumn(name = "idgiao_vien")
        @JsonBackReference
        var teacher: Teacher ? = null,
)