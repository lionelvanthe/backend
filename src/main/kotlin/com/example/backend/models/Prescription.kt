package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*


@Entity
@Table(name = "dan_thuoc")
data class Prescription (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "iddan_thuoc")
        val id: Int = 0,

        @Column(name = "ten_benh")
        @NotBlank
        val pathological: String,

        @Column(name = "ngay_bat_dau")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        val startTime: Date? = null,

        @Column(name = "ngay_ket_thuc")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        val endTime: Date? = null,

        @ManyToOne
        @JoinColumn(name = "idhoc_sinh")
        @JsonBackReference
        var student: Student ? = null,

        @OneToMany(mappedBy = "prescription")
        @JsonBackReference
        var  medicines: List<Medicine>? = null,

        @Column(name = "thoi_gian_tao")
        val createAt: Long = System.currentTimeMillis()

)
