package com.example.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "nguoi_dung", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("username"))])
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @NotNull
    @Column(name = "idnguoi_dung")
    var id: Int = 0,
    @NotBlank
    var username: String,
    @NotBlank
    var password: String,
    @NotBlank
    var role: String = ERole.ROLE_STUDENT.name,

    @OneToOne(mappedBy = "account")
    private val student: Student? = null,

    @OneToOne(mappedBy = "account")
    private val teacher: Teacher? = null

)
