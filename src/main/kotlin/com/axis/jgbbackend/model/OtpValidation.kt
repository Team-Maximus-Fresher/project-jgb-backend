package com.axis.jgbbackend.model

data class OtpValidation(
    val createdAt: String,
    val status: Boolean,
    val stepStatus: String,
    val uniqueIdentifier: String
)