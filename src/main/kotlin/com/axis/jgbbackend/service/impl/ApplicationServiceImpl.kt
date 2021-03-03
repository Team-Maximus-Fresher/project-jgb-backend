package com.axis.jgbbackend.service. impl

import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import com.axis.jgbbackend.util.MappingTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ApplicationServiceImpl(
        @Autowired
        private val applicationRepo: ApplicationRepo,

        @Autowired
        private val mappingTemplate: MappingTemplate
) : ApplicationService {

    override fun getApplicationOfByProductCodeAndCustomerId(productCode: String, customerId: String): ResponseEntity<String> {
        val application = applicationRepo.findByProductCodeAndCustomerId(productCode, customerId).block()
        if(application==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No application(s) found.")
        return application.let { mappingTemplate.filterData(it) }
    }

    override fun getApplicationByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): ResponseEntity<String> {
        val application = applicationRepo.findByApplicationIdAndProductCode(applicationReferenceId, productCode).block()
        if(application==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No application(s) found.")
        return application.let { mappingTemplate.filterData(it) }
    }
}