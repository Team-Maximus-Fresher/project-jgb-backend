package com.axis.jgbbackend.model

data class PreApprovedOffer(
    val accountNumber: String,
    val accountType: String,
    val cibilHit: String,
    val knockoffFlag: Boolean,
    val mScore: Int,
    val preApprovedAmount: Int,
    val promoId: Int,
    val salaryInput: Double,
    val schemeId: Int,
    val solID: String,
    val stepStatus: String,
    val uniqueIdentifier: String
)