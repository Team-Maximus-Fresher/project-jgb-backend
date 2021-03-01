package com.axis.jgbbackend.repository

import com.axis.jgbbackend.model.Application
import com.axis.jgbbackend.model.PersonalOfferLanding
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface PersonalOfferLandingRepo: ReactiveMongoRepository<
        PersonalOfferLanding, String> {
    @Query("{'_id' : ?0}")
    fun findBy_id(_id: String): Mono<PersonalOfferLanding>

//    @Query("{'applicationReferenceId' : ?0, 'productCode' : ?1}")
//    fun findByApplicationIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication>
}