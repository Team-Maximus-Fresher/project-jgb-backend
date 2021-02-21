package com.axis.jgbbackend.model

data class MaxServiceableEMI(
    val stepStatus: String,
    val uniqueIdentifier: String,
    val value: Double
)