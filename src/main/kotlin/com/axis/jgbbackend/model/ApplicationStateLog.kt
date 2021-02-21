package com.axis.jgbbackend.model

data class ApplicationStateLog(
    val _id: String?=null,
    val input: Input?=null,
    val output: Output?=null,
    val status: String?=null,
    val stepExecutionTimeInMillis: String?=null,
    val timestamp: String?=null
)