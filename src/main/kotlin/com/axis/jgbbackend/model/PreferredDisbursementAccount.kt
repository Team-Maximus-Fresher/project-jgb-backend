package com.axis.jgbbackend.model

data class PreferredDisbursementAccount(
    val accType: String,
    val accountFreezeType: String,
    val accountNumber: String,
    val accountOpeningDate: String,
    val accountOwnership: String,
    val accountStatus: String,
    val branchName: String,
    val cmgRiskProfileScore: String,
    val crmRiskScore: String,
    val solId: String,
    val stepStatus: String,
    val uniqueIdentifier: String
)