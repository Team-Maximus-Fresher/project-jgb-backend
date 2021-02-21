package com.axis.jgbbackend.model

data class KycResponse(
    val kycNeeded: Boolean,
    val stepStatus: String,
    val uniqueIdentifier: String
)