package com.axis.jgbbackend.model

data class SelectedLoanDetails(
    val encryptedPan: EncryptedPanXX,
    val loanAmount: Int,
    val loanPurpose: String,
    val optedForWhatsApp: Boolean,
    val pan: String,
    val stepStatus: String,
    val uniqueIdentifier: String
)