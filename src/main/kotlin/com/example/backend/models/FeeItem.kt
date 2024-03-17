package com.example.backend.models

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.sql.Time


@Entity
@Table(name = "khoan_thu")
data class FeeItem (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idkhoan_thu")
        private val id: Int = 0,

        @Column(name = "tieu_de")
        @NotBlank
        var name: String,

        @Column(name = "so_tien_thu")
        var amount : Int,

        @ManyToOne
        @JoinColumn(name = "idhoc_phi")
        @JsonBackReference
        var fee: Fee? = null

)
