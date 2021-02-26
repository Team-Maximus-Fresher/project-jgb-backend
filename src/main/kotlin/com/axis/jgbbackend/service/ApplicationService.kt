package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApplicationService {
    fun getApplicationOfACustomer(productCode: String, customerId: String): PersonalApplication?
    fun getApplicationOfACustomerByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication>
}