package com.example.backend.repository

import com.example.backend.models.DailyMenu
import com.example.backend.models.Food
import org.springframework.data.jpa.repository.JpaRepository

interface FoodRepository: JpaRepository<Food, Int> {

}