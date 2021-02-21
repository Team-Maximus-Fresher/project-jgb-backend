package com.axis.jgbbackend.model

data class ChannelAttribution(
    val ascCode: String,
    val branchCode: String,
    val channel: String,
    val dealerCode: String,
    val dsaCode: String,
    val dseCode: String,
    val employeeCode: String,
    val frozen: Boolean,
    val lastUpdatedTimestamp: String
)