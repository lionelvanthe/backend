package com.example.backend.service

import com.example.backend.repository.FoodRepository
import org.springframework.stereotype.Service

@Service
class FoodService (private val foodRepository: FoodRepository) {
}