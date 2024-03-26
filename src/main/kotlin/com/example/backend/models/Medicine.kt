package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*


@Entity
@Table(name = "thuoc")
data class Medicine (
        @Id
        @Column(name = "idthuoc")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        @Column(name = "ten")
        @NotBlank @Size(max = 45)
        val name: String? = null,

        @Column(name = "hdsd")
        @NotBlank @Size(max = 45)
        val guide: String? = null,

        @Column(name = "chu_thich")
        @NotBlank @Size(max = 45)
        val note: String? = null,

        @ManyToOne
        @JoinColumn(name = "iddan_thuoc")
        @JsonBackReference
        private var prescription: Prescription ? = null
)
