package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

@Entity
@Table(name = "chieu_cao")
data class Height (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idchieu_cao")
        val id: Int = 0,

        @Column(name = "thoi_gian_do")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        var time: Date,

        @Column(name = "gia_tri")
        @NotBlank
        @Email
        var value: Int,

        @ManyToOne
        @JoinColumn(name = "idhoc_sinh")
        @JsonBackReference
        var student: Student ? = null,

        )