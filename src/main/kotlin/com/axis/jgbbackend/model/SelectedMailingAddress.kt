package com.axis.jgbbackend.model

data class SelectedMailingAddress(
    val addressLineFive: String,
    val addressLineFour: String,
    val addressLineOne: String,
    val addressLineThree: String,
    val addressLineTwo: String,
    val city: String,
    val country: String,
    val customerVerified: Boolean,
    val knownAddress: Boolean,
    val kycNeeded: Boolean,
    val pinCode: Int,
    val state: String,
    val stepStatus: String,
    val uniqueIdentifier: String
)