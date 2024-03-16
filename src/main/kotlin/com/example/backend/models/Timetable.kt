package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
@Table(name = "thoi_khoa_bieu")
data class Timetable (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idthoi_khoa_bieu")
        val id: Int = 0,

        @Column(name = "ngay")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        val time: Date,

        @ManyToOne
        @JoinColumn(name = "idlop")
        @JsonBackReference
        var classRoom: ClassRoom ? = null,

        @OneToMany(mappedBy = "timetable")
        @JsonBackReference
        var activities: List<Activity>? = null
)