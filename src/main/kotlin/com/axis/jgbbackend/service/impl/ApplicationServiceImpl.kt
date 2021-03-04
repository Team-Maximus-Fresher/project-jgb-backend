package com.axis.jgbbackend.service. impl

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
    ): Flux<String> {
        return applicationRepo.findByProductCodeAndCustomerId(productCode, customerId).map {
            mappingTemplate.filterData(it)
        }
    }

    override fun getApplicationByApplicationReferenceIdAndProductCode(
        applicationReferenceId: String,
        productCode: String
    ): Mono<String> {
        return applicationRepo.findByApplicationIdAndProductCode(applicationReferenceId, productCode)
            .map {
                mappingTemplate.filterData(it)
            }.switchIfEmpty(Mono.error(NoSuchElementException("Application not found")))
        //return applications.map { application -> mappingTemplate.filterData(application) }.collectList()
    }
}