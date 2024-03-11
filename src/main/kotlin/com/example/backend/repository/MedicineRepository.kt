package com.example.backend.repository

import com.example.backend.models.Medicine
import com.example.backend.models.Prescription
import org.springframework.data.jpa.repository.JpaRepository


interface MedicineRepository: JpaRepository<Medicine, Int> {

    fun findAllByPrescription(prescription: Prescription): List<Medicine>?
}