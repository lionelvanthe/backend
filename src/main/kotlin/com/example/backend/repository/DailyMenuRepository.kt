package com.example.backend.repository

import com.example.backend.models.ClassRoom
import com.example.backend.models.DailyMenu
import com.example.backend.models.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface DailyMenuRepository: JpaRepository<DailyMenu, Int> {
    fun findByClassRoom(classRoom: ClassRoom): List<DailyMenu>
    fun findByClassRoomAndTime(classRoom: ClassRoom, time: Date): DailyMenu

    @Query("SELECT s.foods FROM DailyMenu s WHERE s.id = :id")
    fun findFoodsById(@Param("id") id: Int): List<Food>
}