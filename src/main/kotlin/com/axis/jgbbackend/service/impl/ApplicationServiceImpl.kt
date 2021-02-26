package com.axis.jgbbackend.service. impl


import com.axis.jgbbackend.model.ApplicationStateLog


import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ApplicationServiceImpl(
        @Autowired
        private val applicationRepo: ApplicationRepo
) : ApplicationService {

    var stateLogs = mutableListOf("PersonalOfferFetcher", "PersonalOfferLanding", "PersonalPaSangamCheck", "PersonalLoanOfferRequest",
            "Sanction", "OTPValidation", "KnockOff", "FinnoneAppIdTask", "DisbursementTask"
    )
    fun getFilteredData(personalApplication: Mono<PersonalApplication>): PersonalApplication?
    {
        val application=personalApplication.block()

        val result:MutableList<ApplicationStateLog> = ArrayList()
        for(logs in stateLogs)
       {
          for(res in application?.applicationStateLogs!!)
          {
              if(logs==res._id)
              {
                  println("both are equal")
                  result.add(res)
                  break;
              }
          }

       }
        application?.applicationStateLogs =result
        println(application?.applicationStateLogs)
            println("from pa " + personalApplication.block()?.applicationStateLogs)

        return application
    }

    override fun getApplicationOfACustomer(productCode: String, customerId: String): PersonalApplication? {
        val personalApplication=applicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
       return getFilteredData(personalApplication)
       // return applicationRepo.findByProductCodeAndCustomerId(productCode, customerId)
    }

    override fun getApplicationOfACustomerByApplicationReferenceIdAndProductCode(applicationReferenceId: String, productCode: String): Mono<PersonalApplication> {
        return applicationRepo.findByApplicationIdAndProductCode(applicationReferenceId, productCode)
    }
}