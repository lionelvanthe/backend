package com.example.backend.repository

import com.example.backend.models.ClassRoom
import com.example.backend.models.Student
import com.example.backend.models.Prescription
import com.example.backend.models.TimeOff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


interface PrescriptionRepository: JpaRepository<Prescription, Int> {

    @Query("SELECT to FROM Prescription to " +
            "WHERE to.endTime > :requestDate " +
            "AND to.student = :student")
    fun getAllByStudentAndDate( @Param("requestDate") requestDate: Date,
                                @Param("student")student: Student): Optional<List<Prescription>>

    @Query("SELECT to FROM Prescription to " +
            "WHERE to.student = :student")
    fun getAllByStudent(@Param("student")student: Student): Optional<List<Prescription>>

    @Query("SELECT to FROM Prescription to " +
            "JOIN FETCH to.student s " +
            "JOIN FETCH s.classRoom cr " +
            "WHERE to.endTime > :requestDate " +
            "AND cr = :classRoom")

    fun getPrescriptionByDateAndClass(
            @Param("requestDate") requestDate: Date,
            @Param("classRoom") classRoom: ClassRoom): Optional<List<Prescription>>

}