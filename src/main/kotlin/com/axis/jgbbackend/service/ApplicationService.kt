package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.PersonalApplication
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.spring.web.json.Json

interface ApplicationService {
    fun getApplicationOfByProductCodeAndCustomerId(productCode: String, customerId: String): Flux<String>
    fun getApplicationByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<String>
}