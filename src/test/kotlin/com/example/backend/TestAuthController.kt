package com.example.backend


import com.example.backend.controllers.AuthController
import com.example.backend.payload.request.SignupRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(AuthController::class)
class TestAuthController(
    @Autowired
    private var mvc: MockMvc){

    @Test
    @Throws(Exception::class)
    fun createEmployeeAPI() {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/auth/signup")
                .content(asJsonString(SignupRequest("firstName", "lastName", "admin@mail.com"))!!)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").exists())
    }

    fun asJsonString(obj: Any?): String? {
        return try {
            ObjectMapper().writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}