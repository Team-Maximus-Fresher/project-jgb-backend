package com.axis.jgbbackend.model

data class OpsHumanTaskDetails (
    val uniqueIdentifier:  String? = null,
    val stepStatus: String? = null,
    val finnoneAppId: MutableMap<String, String>? = null
)
