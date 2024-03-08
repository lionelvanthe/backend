package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*


@Entity
@Table(name = "MoiQuanHe")
class Relationship(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "idhoc_sinh")
    @JsonBackReference
    val student: Student,

    @ManyToOne
    @JoinColumn(name = "idphu_huynh")
    @JsonBackReference
    val parent: Parent,

    @Column(name = "MoiQuanHe")
    val relationship: String
)