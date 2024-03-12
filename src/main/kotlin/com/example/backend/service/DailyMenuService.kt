package com.example.backend.service

import com.example.backend.repository.DailyMenuRepository
import org.springframework.stereotype.Service

@Service
class DailyMenuService (private val dailyMenuRepository: DailyMenuRepository) {
}