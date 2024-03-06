package com.example.backend.models

import jakarta.persistence.*


@Entity
@Table(name = "MoiQuanHe")
class Relationship(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "idhoc_sinh", nullable = false)
    private val student: Student? = null,

    @ManyToOne
    @JoinColumn(name = "idphu_huynh", nullable = false)
    private val parent: Parent? = null,

    @Column(name = "MoiQuanHe")
    private val relationship: String? = null
)