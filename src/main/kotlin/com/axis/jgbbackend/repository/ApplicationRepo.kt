package com.axis.jgbbackend.repository

import com.axis.jgbbackend.model.PersonalApplication
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface ApplicationRepo: ReactiveMongoRepository<PersonalApplication, String> {
    @Query("{'customerId' : ?0}")
    fun findByCustomerId(customerId: String): Mono<PersonalApplication>?
}