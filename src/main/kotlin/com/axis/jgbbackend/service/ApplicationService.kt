package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.Application
import com.axis.jgbbackend.model.ReqData
import reactor.core.publisher.Mono

interface ApplicationService {
    fun getFilteredData(_id: String): ReqData
   // fun getApplicationOfACustomerByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication>
}