package com.example.backend.models

import com.example.backend.models.enums.EFee
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.sql.Time
import java.util.*

@Entity
@Table(name = "hoc_phi")
data class Fee (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idhoc_phi")
        val id: Int = 0,

        @Column(name = "tieu_de")
        @NotBlank
        var name: String,

        @Column(name = "tien_da_thanh_toan")
        var paidAmount: Int = 0,

        @Column(name = "han_thanh_toan")
        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        val dueDate: Date,

        @Column(name = "trang_thai")
        var state: String = EFee.UNPAID.name,

        @OneToMany(mappedBy = "fee")
        @JsonBackReference
        var  feeItems: List<FeeItem>? = null,

        @ManyToMany
        @JoinTable(
                name = "hoc_phi_hoc_sinh",
                joinColumns = [JoinColumn(name = "idhoc_phi")],
                inverseJoinColumns = [JoinColumn(name = "idhoc_sinh")]
        )
        var students: MutableSet<Student> = mutableSetOf()

)
