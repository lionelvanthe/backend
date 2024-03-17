package com.example.backend.controllers

import com.example.backend.models.*
import com.example.backend.payload.request.FeeRequest
import com.example.backend.service.ClassRoomService
import com.example.backend.service.FeeItemService
import com.example.backend.service.FeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/fee")
class FeeController(
        private val feeService: FeeService,
        private val feeItemService: FeeItemService,
        private val classRoomService: ClassRoomService) {

    @PostMapping("/{idClass}/create")
    fun createFee(
            @PathVariable idClass: String,
            @RequestBody feeRequest: FeeRequest): ResponseEntity<*> {

        val classRoom = classRoomService.findById(idClass)


        if (classRoom.isPresent) {
            val students = classRoom.get().students
            feeRequest.let {
                val fee = Fee(
                        name = it.name,
                        dueDate = it.dueDate,
                        students = students!!.toMutableSet()

                )
                feeService.createFee(fee)
                val feeItems = ArrayList<FeeItem>()
                it.feeItems.forEach { f ->
                    val feeItem = FeeItem(name = f.name, amount = f.amount, fee = fee)
                    feeItems.add(feeItem)
                    feeItemService.createFeeItem(feeItem)
                }
            }
            return ResponseEntity.ok("create fee successfully")

        } else {
            return ResponseEntity.ok("class not found")
        }

    }

    @GetMapping("/{idStudent}/get")
    fun getFeeByStudent(@PathVariable idStudent: String,
                                @RequestParam(name = "state") state: String): ResponseEntity<*> {

        val fees = feeService.getAllByStudentAndTime(idStudent, state)

        return if (fees.isPresent) {
            return ResponseEntity.ok(fees.get())
        } else {
            ResponseEntity.ok("fee not found")
        }
    }

    @GetMapping("/{idStudent}/get_fee_items")
    fun getFeeItemByStudent(@PathVariable idStudent: String,
                        @RequestParam(name = "feeid") feeId: Int): ResponseEntity<*> {

        val fee = feeService.getById(feeId)
        val feeItems = feeItemService.getByFee(fee)

        return ResponseEntity.ok(feeItems)

    }

}
