package com.example.backend.service

import com.example.backend.models.ClassRoom
import com.example.backend.models.DailyMenu
import com.example.backend.models.Food
import com.example.backend.repository.DailyMenuRepository
import org.springframework.stereotype.Service
import java.util.Date

@Service
class DailyMenuService (private val dailyMenuRepository: DailyMenuRepository) {

    fun createDailyMenu(dailyMenu: DailyMenu) {
        dailyMenuRepository.save(dailyMenu)
    }

    fun findFoodsById(id: Int): List<Food> {
        return dailyMenuRepository.findFoodsById(id)
    }

    fun finDailyMenuByClassRoomAndTime(classRoom: ClassRoom, time: Date): DailyMenu {
        return dailyMenuRepository.findByClassRoomAndTime(classRoom, time)
    }
}