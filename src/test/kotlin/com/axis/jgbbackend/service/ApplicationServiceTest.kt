package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Mono
import java.util.*


@AutoConfigureMockMvc
@SpringBootTest
class ApplicationServiceTest(
    @Autowired var applicationService: ApplicationService
)
{
    @MockBean
    lateinit var applicationRepo: ApplicationRepo

    val personalApplication =
        PersonalApplication("MLP000000000014",null,
            "2021-02-13", null,null,null,null,"840000016",
            "ETB",null,null,null,null,null,null,null,null,null,null,"PERSONAL_ETB_PA",null,
            null,null,null,null,null,null,null,"PA",null,null,null,null,"PERSONAL",null,null,null,null,null,
            "COMPLETE", null, null, null, null
        )

    val output = "{\n" +
            "  \"applicationReferenceId\": \"MLP000000000014\"\n" +
            "  \"customerId\": \"840000016\"\n" +
            "  \"productCode\": \"PERSONAL\"\n" +
            "  \"journeyCode\": \"PERSONAL_ETB_PA\"\n" +
            "  \"applicationStateLogs\": [\n" +
            "    \"PersonalOfferFetcher\": {\n" +
            "      \n" +
            "      \n" +
            "        \"status\": \"not found\"\n" +
            "        \"preApprovedAmount\": \"not found\"\n" +
            "      \n" +
            "    },\n" +
            "    \"PersonalOfferLanding\": {\n" +
            "      \n" +
            "      \n" +
            "        \"status\": \"not found\"\n" +
            "        \"loanAmount\": \"not found\"\n" +
            "        \"loanPurpose\": \"not found\"\n" +
            "      \n" +
            "    }\n" +
            "    \"PersonalPaSangamCheck\": {\n" +
            "      \n" +
            "      \n" +
            "        \"status\": \"not found\"\n" +
            "        \"loanAmount\": \"not found\"\n" +
            "        \"validityDate\": \"not found\"\n" +
            "        \"tenure\": \"not found\"\n" +
            "      \n" +
            "    },\n" +
            "    \"PersonalLoanOfferRequest\": {\n" +
            "      \n" +
            "      \n" +
            "          \"status\": \"not found\"\n" +
            "          \"tenure\": \"not found\"\n" +
            "          \"emi\": \"not found\"\n" +
            "          \"interestRate\": \"not found\"\n" +
            "      \n" +
            "    },\n" +
            "    \"OTPValidation\": {\n" +
            "      \n" +
            "      \n" +
            "        \"status\": \"not found\"\n" +
            "      \n" +
            "    }\n" +
            "    \"KnockOff\": {\n" +
            "      \n" +
            "      \n" +
            "        \"status\": \"not found\"\n" +
            "      \n" +
            "    },\n" +
            "    \"FinnoneAppIdTask\": {\n" +
            "      \n" +
            "      \n" +
            "        \"status\": \"not found\"\n" +
            "        \"appId\": \"not found\"\n" +
            "      \n" +
            "    },\n" +
            "    \"DisbursementTask\": {\n" +
            "      \n" +
            "      \n" +
            "        \"status\": \"not found\"\n" +
            "        \"accountNumber\": \"not found\"\n" +
            "        \"disbursedAmount\": \"not found\"\n" +
            "        \"disbursementDate\": \"not found\"\n" +
            "      \n" +
            "    }\n" +
            "  ]\n" +
            "  \"state\": \"COMPLETE\"\n" +
            "}"

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerId()
    {
        var requiredOutput = ResponseEntity.ok().body(output)
        applicationRepo.save(personalApplication)
        val productCode = "PERSONAL"
        val customerId="840000016"
        Mockito.`when`(applicationRepo.findByProductCodeAndCustomerId("PERSONAL", "840000016")).thenReturn(Mono.just(personalApplication))
        var application = applicationService.getApplicationOfByProductCodeAndCustomerId(productCode, customerId)
        assertEquals(requiredOutput.statusCode, application.statusCode)
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCode()
    {
        var requiredOutput = ResponseEntity.ok().body(output)
        applicationRepo.save(personalApplication)
        val productCode = "PERSONAL"
        val applicationReferenceId="MLP000000000014"
        Mockito.`when`(applicationRepo.findByApplicationIdAndProductCode(applicationReferenceId, productCode)).thenReturn(Mono.just(personalApplication))
        var application = applicationService.getApplicationByApplicationReferenceIdAndProductCode("MLP000000000014", "PERSONAL")
        assertEquals(requiredOutput.statusCode, application.statusCode)
    }
}

