package com.example.backend.controllers

import com.example.backend.models.Parent
import com.example.backend.models.Student
import com.example.backend.payload.request.ParentRequest
import com.example.backend.payload.response.MessageResponse
import com.example.backend.service.user.StudentServiceImp
import com.example.backend.service.user.parent.ParentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/parent")
class ParentController(
        private val parentService: ParentService,
        private val studentServiceImp: StudentServiceImp) {

    @PostMapping("/add")
    fun addInfoParent(
            @RequestParam(name = "idChild") idChild: String,
            @RequestBody parentRequest: ParentRequest): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idChild).get()

        var parent: Parent? = parentService.getParentByNameAndPhone(parentRequest.name, parentRequest.phone)
        if (parent == null) {
            val students = HashSet<Student>()
            students.add(student)
            parent = Parent(name = parentRequest.name, dayOfBirth = parentRequest.dayOfBirth, email = parentRequest.email, relationship = parentRequest.relationShip, phoneNumber = parentRequest.phone, students = students)
        } else {
            parent.students.plus(student)
        }

        student.parents.plus(parent)

        parentService.addParent(parent)
        studentServiceImp.addUser(student)

        return ResponseEntity.ok(MessageResponse("add parent successfully!"))
    }
}