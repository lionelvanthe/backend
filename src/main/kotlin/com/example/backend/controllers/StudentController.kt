package com.example.backend.controllers

import com.example.backend.payload.response.Parents
import com.example.backend.service.RelationshipService
import com.example.backend.service.user.StudentServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/student")
class StudentController(
        private val studentServiceImp: StudentServiceImp,
        private val relationshipService: RelationshipService) {

    @GetMapping("/{idStudent}/parent")
    fun addInfoParent(@PathVariable idStudent: String): ResponseEntity<*> {

        val student = studentServiceImp.getUser(idStudent).get()

        val relationships = relationshipService.getRelationshipByStudent(student)
        return if (relationships == null) {
            ResponseEntity.badRequest().body("parents not found")
        } else {
            val parentList = ArrayList<Parents>()

            relationships.forEach {
                val parents = Parents(it.relationship, it.parent)
                parentList.add(parents)
            }
            ResponseEntity.ok(parentList)
        }
    }
}