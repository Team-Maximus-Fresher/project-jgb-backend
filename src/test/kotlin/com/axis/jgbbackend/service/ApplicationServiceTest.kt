package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.ApplicationStateLog
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono


@AutoConfigureMockMvc
@SpringBootTest
class ApplicationServiceTest
{
    @MockBean
    var applicationRepo: ApplicationRepo?=null

    @Autowired
    var applicationService:ApplicationService?=null

    @Test
    fun testGetApplicationWithCustomerIdAndProductCode()
    {
        val stateLog:MutableList<ApplicationStateLog> =ArrayList()

        var personalApplication = PersonalApplication("MLP000000000014",
                "2021-02-13", "840000016",
                "ETB", "COMPLETE", "PA", "PERSONAL", "PERSONAL_ETB_PA",
                stateLog)

        applicationRepo
                ?.save(personalApplication);
        val productCode = "PERSONAL"
        val customerId="840000016"
        Mockito.`when`(applicationRepo?.findByProductCodeAndCustomerId("PERSONAL","840000016")).thenReturn(Mono.just(personalApplication))
        var app=applicationService?.getApplicationOfACustomer("PERSONAL","840000016")
      // assertEquals(productCode,app?.productCode)
      //  assertEquals(customerId,app?.customerId)
        println(applicationRepo?.findByProductCodeAndCustomerId(productCode,customerId))
        println(applicationService?.getApplicationOfACustomer("PERSONAL","840000016"))
        assertEquals(app,applicationRepo?.findByProductCodeAndCustomerId(productCode,customerId))
    }

    @Test
    fun testGetApplicationWithReferenceIdAndProductCode()
    {
        val stateLog:MutableList<ApplicationStateLog> =ArrayList()

        var personalApplication=PersonalApplication("MLP000000000014",
                "2021-02-13", "840000016",
                "ETB", "COMPLETE", "PA", "PERSONAL", "PERSONAL_ETB_PA",
                stateLog)
        applicationRepo
                ?.save(personalApplication);
        val productCode = "PERSONAL"
        val referenceId="MLP000000000014"
        Mockito.`when`(applicationRepo?.findByApplicationIdAndProductCode(referenceId,productCode)).thenReturn(Mono.just(personalApplication))
        var app=applicationService?.getApplicationOfACustomerByApplicationReferenceIdAndProductCode("MLP000000000014","840000016")
        // assertEquals(productCode,app?.productCode)
        //  assertEquals(customerId,app?.customerId)
        println(applicationRepo?.findByApplicationIdAndProductCode("MLP000000000014","PERSONAL"))
        println(applicationService?.getApplicationOfACustomerByApplicationReferenceIdAndProductCode("MLP000000000014","PERSONAL"))
       assertEquals(app,applicationRepo?.findByApplicationIdAndProductCode("MLP000000000014","840000016"))

    }
}

