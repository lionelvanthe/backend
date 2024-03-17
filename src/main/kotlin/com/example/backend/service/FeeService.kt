package com.example.backend.service

import com.example.backend.models.Fee
import com.example.backend.models.Food
import com.example.backend.repository.FeeRepository
import com.example.backend.repository.FoodRepository
import org.springframework.stereotype.Service
import java.util.Date
import java.util.Optional

@Service
class FeeService (private val feeRepository: FeeRepository) {

    fun createFee(fee: Fee) {
        feeRepository.save(fee)
    }

    fun getAllByStudentAndTime(studentId:String, state: String): Optional<List<Fee>> {
        return feeRepository.findByStudentsIdAndState(studentId, state)
    }

    fun getById(id: Int): Fee {
        return feeRepository.findById(id).get()
    }

    fun updateState(idFee: Int, state: String) {
        val tuitionFee: Fee = feeRepository.findById(idFee).orElse(null)
        tuitionFee.state = state
        feeRepository.save(tuitionFee)
    }
}