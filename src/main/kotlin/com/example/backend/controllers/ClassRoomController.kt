package com.example.backend.controllers

import com.example.backend.models.ClassRoom
import com.example.backend.models.Parent
import com.example.backend.models.Relationship
import com.example.backend.payload.request.ClassRoomRequest
import com.example.backend.payload.response.MessageResponse
import com.example.backend.service.ClassRoomService
import com.example.backend.service.RelationshipService
import com.example.backend.service.user.StudentServiceImp
import com.example.backend.service.user.parent.ParentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/class")
class ClassRoomController(
        private val classRoomService: ClassRoomService,
        private val studentServiceImp: StudentServiceImp) {

    @PostMapping("/add")
    fun addClassRoom(@RequestBody classRoomRequest: ClassRoomRequest): ResponseEntity<*> {

        val classRoom = ClassRoom(classRoomRequest.id, classRoomRequest.name, classRoomRequest.startTime, classRoomRequest.endTime)
        classRoomService.addClassRoom(classRoom)
        return ResponseEntity.ok(MessageResponse("add class successfully!"))
    }

    @PostMapping("/add_student")
    fun addStudentInClassRoom(
            @RequestParam(name = "idClass") idClass: String,
            @RequestBody idStudents: List<String>): ResponseEntity<*> {

        val classRoom: ClassRoom? = classRoomService.findById(idClass)?.get()

        if (classRoom == null) {
            ResponseEntity.badRequest().body("class not found")
        }

        idStudents.forEach {
            val stu = studentServiceImp.getUser(it).get()
            stu.classRoom = classRoom
            studentServiceImp.addUser(stu)
        }

        return ResponseEntity.ok(MessageResponse("add student into class successfully!"))
    }

    @GetMapping("/{idClass}/student")
    fun getStudentInClassRoom(@PathVariable idClass: String): ResponseEntity<*> {

        val classRoom = classRoomService.findById(idClass)?.get()

        if (classRoom == null) {
            return ResponseEntity.badRequest().body("class not found")
        } else {
            return ResponseEntity.ok(studentServiceImp.findStudentByClass(classRoom))
        }

    }
}