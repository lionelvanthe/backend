package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

@Entity
@Table(name = "giao_vien")
class Teacher(
        @Id
        @Column(name = "idgiao_vien")
        override val id: String,

        @Column(name = "ho_ten")
        @NotBlank
        @Size(max = 255)
        override val name: String,

        @Column(name = "ngay_sinh")
        @NotBlank
        override val dayOfBirth: Date,

        @Column(name = "gioi_tinh")
        @NotBlank
        @Size(max = 45)
        override val gender: String,

        @Column(name = "dia_chi")
        @NotBlank
        @Size(max = 255)
        override val address: String,

        @Column(name = "email")
        @NotBlank @Size(max = 255)
        @Email
        val email: String,

        @Column(name = "so_dien_thoai")
        @NotBlank @Size(max = 255)
        val phoneNumber: String,

        @OneToOne
        @JoinColumn(name = "user_id")
        val account: Account? = null,

        @OneToMany(mappedBy = "teacher")
        @JsonBackReference
        var  activity: List<Activity>? = null
): User()
