package com.example.backend.repository

import com.example.backend.models.DailyMenu
import org.springframework.data.jpa.repository.JpaRepository

interface DailyMenuRepository: JpaRepository<DailyMenu, Int> {
}