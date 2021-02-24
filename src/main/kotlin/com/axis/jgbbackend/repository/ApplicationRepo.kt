package com.axis.jgbbackend.repository

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ApplicationRepo: ReactiveMongoRepository<PersonalApplication, String> {
    @Query("{'productCode' : ?0, 'customerId' : ?1}")
    fun findByProductCodeAndCustomerId(productCode: String, customerId: String): Mono<PersonalApplication>

    @Query("{'applicationReferenceId' : ?0, 'productCode' : ?1}")
    fun findByApplicationIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication>
}