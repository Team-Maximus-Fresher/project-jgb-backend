package com.axis.jgbbackend.service. impl

import com.axis.jgbbackend.exception.ApplicationNotFoundException
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import com.axis.jgbbackend.util.MappingTemplate
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.spring.web.json.Json

@Service
class ApplicationServiceImpl(
        @Autowired
        private val applicationRepo: ApplicationRepo,

        @Autowired
        private val mappingTemplate: MappingTemplate
) : ApplicationService {

    override fun getApplicationOfByProductCodeAndCustomerId(
        productCode: String,
        customerId: String
    ): Mono<MutableList<Json>> {
        return applicationRepo.findByProductCodeAndCustomerId(productCode, customerId).map {
            mappingTemplate.filterData(it)
        }.switchIfEmpty(Mono.error(ApplicationNotFoundException())).collectList()
    }

    override fun getApplicationOfByProductCodeAndMobileNumber(
        productCode: String,
        mobileNumber: String
    ): Mono<MutableList<Json>> {
        return applicationRepo.findByProductCodeAndMobileNumber(productCode, mobileNumber).map {
            mappingTemplate.filterData(it)
        }.switchIfEmpty(Mono.error(ApplicationNotFoundException())).collectList()
    }

    override fun getApplicationByApplicationReferenceIdAndProductCode(
        productCode: String,
        applicationReferenceId: String
    ): Mono<Json> {
        return applicationRepo.findByApplicationIdAndProductCode(productCode, applicationReferenceId)
            .map {
                mappingTemplate.filterData(it)
            }.switchIfEmpty(Mono.error(ApplicationNotFoundException()))
    }
}