package com.axis.jgbbackend.model

data class InsurancePremium(
    val basicPremium: Double,
    val stepStatus: String,
    val totalPremium: Int,
    val uniqueIdentifier: String
)