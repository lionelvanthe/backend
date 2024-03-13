package com.example.backend.service

import com.example.backend.models.Food
import com.example.backend.repository.FoodRepository
import org.springframework.stereotype.Service

@Service
class FoodService (private val foodRepository: FoodRepository) {

    fun createFood(food: Food) {
        foodRepository.save(food)
    }
}