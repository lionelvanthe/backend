package com.example.backend.service

import com.example.backend.models.ClassRoom
import com.example.backend.models.Student
import com.example.backend.models.Prescription
import com.example.backend.repository.PrescriptionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PrescriptionService (private val prescriptionRepository: PrescriptionRepository) {
    fun createPrescription(prescription: Prescription): Prescription {
        return prescriptionRepository.save(prescription)
    }

    fun getPrescriptionByStudent(student: Student, date: Date): Prescription? {
        return prescriptionRepository.getAllByStudentAndDate(date, student)
    }

    fun findById(id: Int): Optional<Prescription>? {
        return prescriptionRepository.findById(id)
    }

    fun update(prescription: Prescription) {
        prescriptionRepository.save(prescription)
    }

    fun getPrescriptionByDateAndClass(requestDate: Date, classRoom: ClassRoom): List<Prescription>? {
        return prescriptionRepository.getPrescriptionByDateAndClass(requestDate, classRoom)
    }
}