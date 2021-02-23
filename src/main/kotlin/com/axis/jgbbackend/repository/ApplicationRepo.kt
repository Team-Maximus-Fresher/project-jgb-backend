package com.axis.jgbbackend.repository

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface ApplicationRepo: ReactiveMongoRepository<PersonalApplication, String> {
    @Query("{'productCode' : ?0, 'customerId' : ?1}")
    fun findByProductCodeAndCustomerId(productCode: String, customerId: String): Mono<PersonalApplication>

    @Query("{'applicationReferenceId' : ?0}")
    fun findByApplicationReferenceId(applicationReferenceId: String): Mono<PersonalApplication>
}