package com.example.backend.models

import com.example.backend.models.enums.ETimeOff
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

@Entity
@Table(name = "don_xin_nghi")
data class TimeOff(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "iddon_xin_nghi")
        private val id: Int = 0,

        @Column(name = "idgiao_vien")
        @NotBlank @Size(max = 45)
        val idTeacher: String? = null,

        @Column(name = "ngay_bat_dau")
        @NotBlank
        val startTime: Date,

        @Column(name = "ngay_ket_thuc")
        val endTime: Date,

        @Column(name = "trang_thai")
        val state: String = ETimeOff.PENDING.name,

        @Column(name = "ghi_chu")
        val note: String? = null,

        @Column(name = "ly_do")
        val reason: String,

        @ManyToOne
        @JoinColumn(name = "idhoc_sinh")
        @JsonBackReference
        var student: Student ? = null,

)
