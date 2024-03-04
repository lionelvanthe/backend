package com.example.backend.repository

import com.example.backend.models.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, Int> {
    fun findByUsername(username: String?): Optional<Account>?
    fun existsByUsername(username: String?): Boolean?
}
