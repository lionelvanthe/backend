package com.example.backend.repository

import com.example.backend.models.Fee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface FeeRepository: JpaRepository<Fee, Int> {


    @Query("SELECT tf FROM Fee tf JOIN tf.students s WHERE s.id = :studentId AND tf.state = :state")
    fun findByStudentsIdAndState(@Param("studentId") studentId: String, @Param("state") state: String): Optional<List<Fee>>

}