package com.axis.jgbbackend.model

data class SangamResult(
    val check: String,
    val `data`: DataXXX,
    val status: String,
    val stepStatus: String,
    val uniqueIdentifier: String
)