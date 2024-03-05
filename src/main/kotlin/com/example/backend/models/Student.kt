package com.example.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*
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

        @ManyToMany(mappedBy = "students")
        @Column(length = 45)
        var parents: Set<Parent> = HashSet()
): User()
