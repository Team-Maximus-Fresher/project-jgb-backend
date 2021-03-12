package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.PersonalApplication
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.spring.web.json.Json

interface ApplicationService {
    fun getApplicationByProductCodeAndCustomerId(productCode: String, customerId: String): Mono<MutableList<Json>>
    fun getApplicationByProductCodeAndMobileNumber(productCode: String, mobileNumber: String): Mono<MutableList<Json>>
    fun getApplicationByApplicationReferenceIdAndProductCode(productCode: String, applicationReferenceId: String): Mono<Json>
}