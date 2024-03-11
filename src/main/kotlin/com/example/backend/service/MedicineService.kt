package com.example.backend.service

import com.example.backend.models.Medicine
import com.example.backend.models.Prescription
import com.example.backend.repository.MedicineRepository
import org.springframework.stereotype.Service

@Service
class MedicineService (private val medicineRepository: MedicineRepository) {

    fun createMedicine(medicine: Medicine) {
        medicineRepository.save(medicine)
    }

    fun getMedicineByPrescription(prescription: Prescription): List<Medicine>? {
        return medicineRepository.findAllByPrescription(prescription)
    }
}