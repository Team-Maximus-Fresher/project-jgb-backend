package com.axis.jgbbackend.model

data class Input(
    val accountNumber: String?=null,
    val appId: String?=null,
    val breakdown: List<Breakdown>?=null,
    val check: String?=null,
    val `data`: Data?=null,
    val details: Details?=null,
    val disbursedAmount: String?=null,
    val disbursementDate: String?=null,
    val fraudIndicators: List<Any>?=null,
    val fraudPresent: Boolean?=null,
    val loanAmount: Int?=null,
    val loanPurpose: String?=null,
    val nominee: Any?=null,
    val opted: Boolean?=null,
    val optedForWhatsApp: Boolean?=null,
    val otp: String?=null,
    val status: String?=null,
    val token: String?=null,
    val transactionId: String?=null
)