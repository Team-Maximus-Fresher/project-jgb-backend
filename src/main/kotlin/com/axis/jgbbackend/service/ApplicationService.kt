package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Mono

interface ApplicationService {
    fun getApplicationOfByProductCodeAndCustomerId(productCode: String, customerId: String): ResponseEntity<String>
    fun getApplicationByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): ResponseEntity<String>
}