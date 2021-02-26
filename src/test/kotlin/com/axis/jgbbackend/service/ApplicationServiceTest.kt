package com.axis.jgbbackend.service

import com.axis.jgbbackend.JourneyGraphBoardBackendApplication
import com.axis.jgbbackend.model.ApplicationStateLog
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.runApplication
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import reactor.core.publisher.Mono


@AutoConfigureMockMvc
@SpringBootTest
class ApplicationServiceTest(
        @Autowired var applicationService: ApplicationService

)
{
    @MockBean
    lateinit var applicationRepo: ApplicationRepo

    @Test
    fun main() {

    }

    @Test
    fun testGetApplicationWithCustomerIdAndProductCode()
    {
        val stateLog:MutableList<ApplicationStateLog> =ArrayList()

        var personalApplication = PersonalApplication("MLP000000000014",
                "2021-02-13", "840000016",
                "ETB", "COMPLETE", "PA", "PERSONAL", "PERSONAL_ETB_PA",
                stateLog)

        applicationRepo.
                save(personalApplication);
        val productCode = "PERSONAL"
        val customerId="840000016"
        Mockito.`when`(applicationRepo.findByProductCodeAndCustomerId("PERSONAL", "840000016")).thenReturn(Mono.just(personalApplication))
        var app=applicationService.getApplicationOfACustomer("PERSONAL", "840000016")
        assertEquals(app, applicationService.getApplicationOfACustomer(productCode, customerId))

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
                .save(personalApplication);
        val productCode = "PERSONAL"
        val referenceId="MLP000000000014"
        Mockito.`when`(applicationRepo.findByApplicationIdAndProductCode(referenceId, productCode)).thenReturn(Mono.just(personalApplication))
        var application=applicationService.getApplicationOfACustomerByApplicationReferenceIdAndProductCode("MLP000000000014", "PERSONAL")

        assertEquals(application, applicationService.getApplicationOfACustomerByApplicationReferenceIdAndProductCode(referenceId, productCode))

    }
}

