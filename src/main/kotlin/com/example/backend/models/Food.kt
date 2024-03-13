package com.example.backend.models

import com.example.backend.models.enums.ETimeOff
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.sql.Time
import java.util.*


@Entity
@Table(name = "mon_an")
data class Food (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idmon_an")
        private val id: Int = 0,

        @Column(name = "ten")
        @NotBlank
        var name: String,

        @Column(name = "nguyen_lieu")
        @NotBlank
        var ingredients: String? = null,

        @Column(name = "dinh_duong")
        var nutrition: String? = null,

        @Column(name = "url_anh")
        val urlImage: String? = null,

        @Column(name = "thoi_gian_bat_dau")
        @NotBlank
        val startTime: Time,

        @Column(name = "thoi_gian_ket_thuc")
        val endTime: Time,

        @ManyToMany(mappedBy = "foods")
        private var menus: MutableSet<DailyMenu> = mutableSetOf()

)