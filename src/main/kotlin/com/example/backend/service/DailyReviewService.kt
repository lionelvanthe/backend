package com.example.backend.service

import com.example.backend.models.DailyReview
import com.example.backend.models.Student
import com.example.backend.repository.DailyReviewRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DailyReviewService(private val dailyReviewRepository: DailyReviewRepository) {

    fun createDailyReview(dailyReview: DailyReview) {
        dailyReviewRepository.save(dailyReview)
    }

    fun getDailyReviewByStudentAndTime(student: Student, time: Date): Optional<DailyReview> {
        return dailyReviewRepository.findByStudentAndTimeReview(student, time)
    }
}