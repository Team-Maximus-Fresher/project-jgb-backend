package com.axis.jgbbackend.repository

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
interface ApplicationRepo: ReactiveMongoRepository<PersonalApplication, String> {
    @Query("{'productCode' : ?0, 'customerId' : ?1}")
    fun findByProductCodeAndCustomerId(productCode: String, customerId: String): Flux<PersonalApplication>

    @Query("{'productCode' : ?0, 'applicationReferenceId' : ?1}")
    fun findByApplicationIdAndProductCode(productCode: String, applicationReferenceId: String): Mono<PersonalApplication>
}