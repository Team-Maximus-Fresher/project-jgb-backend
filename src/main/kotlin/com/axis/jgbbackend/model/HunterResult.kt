package com.axis.jgbbackend.model

data class HunterResult(
    val check: String,
    val `data`: DataXX,
    val status: String,
    val stepStatus: String,
    val uniqueIdentifier: String
)