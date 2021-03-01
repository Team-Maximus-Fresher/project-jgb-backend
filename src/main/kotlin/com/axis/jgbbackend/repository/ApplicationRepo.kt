package com.axis.jgbbackend.repository

import com.axis.jgbbackend.model.Application
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ApplicationRepo: ReactiveMongoRepository<
        Application, String> {
    @Query("{'loanPurpose' : ?0}")
    fun findByLoanPurpose(loanPurpose: String): Mono<Application>

//    @Query("{'applicationReferenceId' : ?0, 'productCode' : ?1}")
//    fun findByApplicationIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication>
}