package com.example.backend.models

import com.example.backend.models.enums.EStudent
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

@Entity
@Table(name = "hoc_sinh")
class Student (
        @Id
        @Column(name = "idhoc_sinh")
        override val id: String,

        @Column(name = "ho_ten")
        @NotBlank @Size(max = 255)
        override val name: String,

        @Column(name = "ngay_sinh")
        @NotBlank
        override val dayOfBirth: Date,

        @Column(name = "gioi_tinh")
        @NotBlank @Size(max = 45)
        override val gender: String,

        @Column(name = "dia_chi")
        @NotBlank @Size(max = 255)
        override val address: String,

        @OneToOne
        @JoinColumn(name = "user_id")
        private val account: Account,

        @ManyToOne
        @JoinColumn(name = "idlop")
        @JsonBackReference
        var classRoom: ClassRoom ? = null,

        @OneToMany(mappedBy = "student")
        @JsonBackReference
        var  attendances: List<Attendance>? = null,

        @OneToMany(mappedBy = "student")
        @JsonBackReference
        var  timeOffs: List<TimeOff>? = null,

        @OneToMany(mappedBy = "student")
        @JsonBackReference
        var  prescriptions: List<Prescription>? = null,


        @OneToMany(mappedBy = "student")
        @JsonBackReference
        var  dailyReview: List<DailyReview>? = null,

        @ManyToMany(mappedBy = "students")
        var fees: MutableSet<Fee> = mutableSetOf(),

        @Column(name = "loai")
        var type: String = EStudent.NORMAL.name,

        @OneToMany(mappedBy = "student")
        @JsonBackReference
        var  heights: List<Height> = ArrayList(),

        @OneToMany(mappedBy = "student")
        @JsonBackReference
        var  weights: List<Weight> = ArrayList(),

        ): User()
