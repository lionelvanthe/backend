package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
@Table(name = "thuc_don")
data class DailyMenu (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idthuc_don")
        val id: Int = 0,

        @Column(name = "ngay")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        val time: Date,

        @ManyToOne
        @JoinColumn(name = "idlop")
        @JsonBackReference
        var classRoom: ClassRoom ? = null,

        @ManyToMany
        @JoinTable(
                name = "thuc_don_mon_an",
                joinColumns = [JoinColumn(name = "idthuc_don")],
                inverseJoinColumns = [JoinColumn(name = "idmon_an")]
        )
        var foods: MutableSet<Food> = mutableSetOf()
)