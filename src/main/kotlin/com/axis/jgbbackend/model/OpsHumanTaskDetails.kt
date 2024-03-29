package com.axis.jgbbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class OpsHumanTaskDetails (
    val uniqueIdentifier:  String? = null,
    val stepStatus: String? = null,
    val finnoneAppId: MutableMap<String, String>? = null
)
