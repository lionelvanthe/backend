package com.example.backend.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "nguoi_dung", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("username"))])
data class User(
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
    var role: String = ERole.PARENT.name,

    @OneToOne(mappedBy = "user")
    private val parent: Parent? = null,

    @OneToOne(mappedBy = "user")
    private val teacher: Teacher? = null

)
