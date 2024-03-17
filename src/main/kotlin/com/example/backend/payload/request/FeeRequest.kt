package com.example.backend.payload.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class FeeRequest(

        var name: String,

        var paidAmount: Int = 0,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+7")
        val dueDate: Date,

        var  feeItems: List<FeeItemRequest>,

)