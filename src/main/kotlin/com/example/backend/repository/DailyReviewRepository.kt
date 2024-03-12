package com.example.backend.repository

import com.example.backend.models.ClassRoom
import com.example.backend.models.DailyReview
import com.example.backend.models.Student
import com.example.backend.models.TimeOff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Date
import java.util.Optional


interface DailyReviewRepository: JpaRepository<DailyReview, Int> {

    fun findByStudentAndTimeReview(student: Student, time: Date): Optional<DailyReview>
}