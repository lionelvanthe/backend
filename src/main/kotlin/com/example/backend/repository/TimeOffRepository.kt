package com.example.backend.repository

import com.example.backend.models.ClassRoom
import com.example.backend.models.Student
import com.example.backend.models.TimeOff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Date
import java.util.Optional


interface TimeOffRepository: JpaRepository<TimeOff, Int> {

    @Query("SELECT to FROM TimeOff to " +
            "WHERE to.endTime > :requestDate " +
            "AND to.student = :student")
    fun getAllByStudentAndDate( @Param("requestDate") requestDate: Date,
                                @Param("student")student: Student): Optional<List<TimeOff>>

    @Query("SELECT to FROM TimeOff to " +
            "WHERE to.endTime > :startTime AND to.startTime < :endTime " +
            "AND to.student = :student")
    fun getAllByStudentAndDate( @Param("startTime") startTime: Date,
                                @Param("endTime") endTime: Date,
                                @Param("student")student: Student): Optional<List<TimeOff>>
    @Query("SELECT to FROM TimeOff to " +
            "WHERE to.student = :student")
    fun getAllByStudent(@Param("student")student: Student): Optional<List<TimeOff>>


    @Query("SELECT to FROM TimeOff to " +
            "JOIN FETCH to.student s " +
            "JOIN FETCH s.classRoom cr " +
            "WHERE to.endTime > :requestDate " +
            "AND cr = :classRoom")

    fun getTimeOffByDateAndClass(
            @Param("requestDate") requestDate: Date,
            @Param("classRoom") classRoom: ClassRoom): Optional<List<TimeOff>>
}