package com.example.backend.controllers

import com.example.backend.models.Height
import com.example.backend.models.Weight
import com.example.backend.payload.request.WeightOrHeightRequest
import com.example.backend.payload.response.MessageResponse
import com.example.backend.service.HeightService
import com.example.backend.service.WeightService
import com.example.backend.service.user.StudentServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/heightandweight")
class HeightAndWeightController(
        private val studentServiceImp: StudentServiceImp,
        private val heightService: HeightService,
        private val weightService: WeightService) {

    @PostMapping("/{idStudent}/create_height")
    fun createHeight(@PathVariable idStudent: String,
                 @RequestBody weightOrHeightRequest: WeightOrHeightRequest): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent).get()

        weightOrHeightRequest.let {
            val height = Height(value = it.value, time = it.time, student = student)
            heightService.createHeight(height)
        }

        return ResponseEntity.ok(MessageResponse("create height successfully!"))

    }

    @GetMapping("/{idStudent}/get_height")
    fun getHeight(@PathVariable idStudent: String): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent).get()
        val height = heightService.getByStudent(student)
        return ResponseEntity.ok(height)

    }

    @PostMapping("/{idHeight}/updat_value_height")
    fun updateValueHeight(@PathVariable idHeight: Int,
                          @RequestBody value: Int): ResponseEntity<*> {

        return if (heightService.updateValueHeight(idHeight, value) == null) {

            ResponseEntity.badRequest().body("update failed")
        } else {
            ResponseEntity.ok("update successfully")
        }

    }

    @PostMapping("/{idHeight}/update_time_height")
    fun updateTimeHeight(@PathVariable idHeight: Int,
                          @RequestBody time: LocalDate): ResponseEntity<*> {

        return if (heightService.updateTimeHeight(idHeight, Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant())) == null) {

            ResponseEntity.badRequest().body("update failed")
        } else {
            ResponseEntity.ok("update successfully")
        }

    }

    @PostMapping("/{idStudent}/create_weight")
    fun createWeight(@PathVariable idStudent: String,
                     @RequestBody weightOrHeightRequest: WeightOrHeightRequest): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent).get()

        weightOrHeightRequest.let {
            val weight = Weight(value = it.value, time = it.time, student = student)
            weightService.createWeight(weight)
        }

        return ResponseEntity.ok(MessageResponse("create height successfully!"))

    }

    @GetMapping("/{idStudent}/get_weight")
    fun getWeight(@PathVariable idStudent: String): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent).get()
        val weight = weightService.getByStudent(student)
        return ResponseEntity.ok(weight)

    }

    @PostMapping("/{idWeight}/update_value_weight")
    fun updateValueWeight(@PathVariable idWeight: Int,
                          @RequestBody value: Int): ResponseEntity<*> {

        return if (weightService.updateValueWeight(idWeight, value) == null) {

            ResponseEntity.badRequest().body("update failed")
        } else {
            ResponseEntity.ok("update successfully")
        }

    }

    @PostMapping("/{idWeight}/update_time_weight")
    fun updateTimeWeight(@PathVariable idWeight: Int,
                         @RequestBody time: LocalDate): ResponseEntity<*> {

        return if (weightService.updateTimeWeight(idWeight, Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant())) == null) {

            ResponseEntity.badRequest().body("update failed")
        } else {
            ResponseEntity.ok("update successfully")
        }

    }
}