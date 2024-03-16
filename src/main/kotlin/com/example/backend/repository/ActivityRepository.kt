package com.example.backend.repository

import com.example.backend.models.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActivityRepository: JpaRepository<Activity, Int> {

}