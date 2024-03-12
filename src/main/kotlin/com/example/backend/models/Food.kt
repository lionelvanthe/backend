package com.example.backend.models

import com.example.backend.models.enums.ETimeOff
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*


@Entity
@Table(name = "mon_an")
data class Food (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idmon_an")
        private val id: Int = 0,

        @Column(name = "nguyen_lieu")
        @NotBlank @Size(max = 45)
        var ingredients: String? = null,

        @Column(name = "dinh_duong")
        var nutrition: String? = null,

        @Column(name = "url_anh")
        val urlImage: String? = null,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        @Column(name = "thoi_gian_bat_dau")
        @NotBlank
        val startTime: Date,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
        @Column(name = "thoi_gian_ket_thuc")
        val endTime: Date,

        @ManyToMany(mappedBy = "foods")
        var menus: MutableSet<DailyMenu> = mutableSetOf()

)