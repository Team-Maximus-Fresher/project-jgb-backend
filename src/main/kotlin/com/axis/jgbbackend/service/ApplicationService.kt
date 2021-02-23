package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApplicationService {
    fun getAllPersonalLoanApplications(): Flux<PersonalApplication?>?
    fun getApplicationOfACustomer(productCode: String, customerId: String): Mono<PersonalApplication>?
    fun getApplicationOfACustomerByApplicationReferenceId(applicationReferenceId: String): Mono<ResponseEntity<PersonalApplication?>?>?
    fun savePersonalApplication(@RequestBody personalApplication: PersonalApplication?): Mono<PersonalApplication?>?
}