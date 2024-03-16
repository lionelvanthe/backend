package com.example.backend.service

import com.example.backend.models.Activity
import com.example.backend.repository.ActivityRepository
import org.springframework.stereotype.Service

@Service
class ActivityService(private val activityRepository: ActivityRepository) {

    fun createActivity(activity: Activity) {
        activityRepository.save(activity)
    }
}