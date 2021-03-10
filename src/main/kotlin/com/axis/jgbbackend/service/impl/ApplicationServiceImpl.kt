package com.axis.jgbbackend.service.impl

import com.axis.jgbbackend.exception.ApplicationNotFoundException
import com.axis.jgbbackend.repository.AutoApplicationRepo
import com.axis.jgbbackend.repository.PersonalApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import com.axis.jgbbackend.util.MappingTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.spring.web.json.Json

@Service
class ApplicationServiceImpl(
    @Autowired
    private val personalApplicationRepo: PersonalApplicationRepo,

    @Autowired
    private val autoApplicationRepo: AutoApplicationRepo,

    @Autowired
    private val mappingTemplate: MappingTemplate
) : ApplicationService {

    override fun getApplicationOfByProductCodeAndCustomerId(
        productCode: String,
        customerId: String
    ): Mono<MutableList<Json>> {
        if(productCode == "PERSONAL") {
            return personalApplicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
                .map { mappingTemplate.filterData(it) }
                .switchIfEmpty(Flux.error(ApplicationNotFoundException())).collectList()
        }
        return autoApplicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
            .map { mappingTemplate.filterData(it) }
            .switchIfEmpty(Flux.error(ApplicationNotFoundException())).collectList()
    }

    override fun getApplicationOfByProductCodeAndMobileNumber(
        productCode: String,
        mobileNumber: String
    ): Mono<MutableList<Json>> {
        if(productCode == "PERSONAL") {
            return personalApplicationRepo.findByProductCodeAndMobileNumber(productCode, mobileNumber)
                .map { mappingTemplate.filterData(it) }
                .switchIfEmpty(Flux.error(ApplicationNotFoundException())).collectList()
        }
        return autoApplicationRepo.findByProductCodeAndMobileNumber(productCode, mobileNumber)
            .map { mappingTemplate.filterData(it) }
            .switchIfEmpty(Flux.error(ApplicationNotFoundException())).collectList()
    }

    override fun getApplicationByApplicationReferenceIdAndProductCode(
        productCode: String,
        applicationReferenceId: String
    ): Mono<Json> {
        if(productCode == "PERSONAL") {
            return personalApplicationRepo.findByApplicationIdAndProductCode(productCode, applicationReferenceId)
                .map { mappingTemplate.filterData(it) }
                .switchIfEmpty(Mono.error(ApplicationNotFoundException()))
        }
        return autoApplicationRepo.findByApplicationIdAndProductCode(productCode, applicationReferenceId)
            .map { mappingTemplate.filterData(it) }
            .switchIfEmpty(Mono.error(ApplicationNotFoundException()))
    }
}