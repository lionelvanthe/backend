package com.example.backend.repository

import com.example.backend.models.Fee
import com.example.backend.models.FeeItem
import org.springframework.data.jpa.repository.JpaRepository

interface FeeItemRepository: JpaRepository<FeeItem, Int> {

    fun findAllByFee(fee: Fee): List<FeeItem>
}