package com.example.backend.controllers

import com.example.backend.models.*
import com.example.backend.payload.request.MedicineRequest
import com.example.backend.payload.request.PrescriptionRequest
import com.example.backend.payload.response.PrescriptionResponse
import com.example.backend.service.ClassRoomService
import com.example.backend.service.MedicineService
import com.example.backend.service.PrescriptionService
import com.example.backend.service.user.StudentServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/prescription")
class PrescriptionController(
        private val prescriptionService: PrescriptionService,
        private val classRoomService: ClassRoomService,
        private val studentServiceImp: StudentServiceImp,
        private val medicineService: MedicineService) {

    @PostMapping("/{idStudent}/create")
    fun createPrescription(
            @PathVariable idStudent: String,
            @RequestBody prescriptionRequest: PrescriptionRequest): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent).get()


        prescriptionRequest.let {
            val prescription = Prescription(
                    startTime = it.startTime,
                    endTime = it.endTime,
                    pathological = it.pathological,
                    student = student)



            val prescriptionSaved = prescriptionService.createPrescription(prescription)
            it.medicines.map { medi ->
                val medicine = Medicine(name = medi.name, note = medi.note, guide = medi.guide, prescription = prescriptionSaved)
                medicineService.createMedicine(medicine)
            }

        }
        return ResponseEntity.ok("creat prescription successfully")
    }

    @GetMapping("/{idStudent}/get_by_student")
    fun getPrescriptionByStudent(@PathVariable idStudent: String,
                            @RequestParam (name = "time") time: LocalDate): ResponseEntity<*> {
        val student = studentServiceImp.getUser(idStudent).get()

        val prescription = prescriptionService.getPrescriptionByStudent(student,
                Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant()))

        return if (prescription != null) {

            val medicines = medicineService.getMedicineByPrescription(prescription)
            val prescriptionResponse = prescription.let {
                PrescriptionResponse(it.pathological, startTime = it.startTime!!, endTime = it.endTime!!, medicines =  medicines!!)
            }
            ResponseEntity.ok(prescriptionResponse)
        } else {
            ResponseEntity.ok("nothing")
        }
    }
    @GetMapping("/{idClass}/get_by_class")
    fun getPrescriptionByDateAndClass(
            @PathVariable idClass: String,
            @RequestParam (name = "time") time: LocalDate): ResponseEntity<*> {
        val classRoom = classRoomService.findById(idClass)?.get() ?: return ResponseEntity.ok("try again")

        val prescriptions = prescriptionService.getPrescriptionByDateAndClass(
                Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                classRoom)

        return if (prescriptions != null) {
            val prescriptionsResponse = prescriptions.map {
                val medicines = medicineService.getMedicineByPrescription(it)
                PrescriptionResponse(pathological = it.pathological, startTime = it.startTime!!, endTime = it.endTime!!, medicines = medicines!!)
            }
            ResponseEntity.ok(prescriptionsResponse)
        } else {
            ResponseEntity.ok("nothing")
        }
    }

}
