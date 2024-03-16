package com.example.backend.controllers

import com.example.backend.models.DailyMenu
import com.example.backend.models.DailyReview
import com.example.backend.models.Food
import com.example.backend.payload.request.DailyMenuRequest
import com.example.backend.payload.request.DailyReviewRequest
import com.example.backend.service.ClassRoomService
import com.example.backend.service.DailyMenuService
import com.example.backend.service.FoodService
import com.example.backend.service.user.StudentServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/dailymenu")
class DailyMenuController(
        private val classRoomService: ClassRoomService,
        private val dailyMenuService: DailyMenuService,
        private val foodService: FoodService,
        private val studentService: StudentServiceImp
) {

    @PostMapping("/{idClass}/create")
    fun createDailyMenu(
            @PathVariable idClass: String,
            @RequestBody dailyMenuRequest: DailyMenuRequest): ResponseEntity<*> {

        val classRoom = classRoomService.findById(idClass)

        if (classRoom.isPresent) {
            dailyMenuRequest.let {
                val dailyMenu = DailyMenu(
                        time = it.time,
                        classRoom = classRoom.get()
                )
                val foods = ArrayList<Food>()
                it.foods.forEach { f ->
                    val food = Food(name = f.name, ingredients = f.ingredients, nutrition = f.nutrition, startTime = f.startTime, endTime = f.endTime)
                    foods.add(food)
                    foodService.createFood(food)
                }
                dailyMenu.foods = foods.toMutableSet()
                dailyMenuService.createDailyMenu(dailyMenu)

            }

            return ResponseEntity.ok("create menu successfully")

        } else {
            return ResponseEntity.ok("class not found")
        }

    }

    @GetMapping("/{idStudent}/get")
    fun getDailyMenuByStudent(
            @PathVariable idStudent: String,
            @RequestParam (name = "time") time: LocalDate): ResponseEntity<*> {

        val student = studentService.getUser(idStudent).get()

        val classRoom = student.classRoom

        val dailyMenu = dailyMenuService.finDailyMenuByClassRoomAndTime(classRoom!!,
                Date.from(time.atStartOfDay(ZoneId.systemDefault()).toInstant()))

        val foods = dailyMenuService.findFoodsById(dailyMenu.id)

        return ResponseEntity.ok(foods)

    }
}