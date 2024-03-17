package com.example.backend.service

import com.example.backend.models.Fee
import com.example.backend.models.FeeItem
import com.example.backend.repository.FeeItemRepository
import org.springframework.stereotype.Service

@Service
class FeeItemService (private val feeItemRepository: FeeItemRepository) {

    fun createFeeItem(feeItem: FeeItem) {
        feeItemRepository.save(feeItem)
    }

    fun getByFee(fee: Fee): List<FeeItem> {
        return feeItemRepository.findAllByFee(fee)
    }
}