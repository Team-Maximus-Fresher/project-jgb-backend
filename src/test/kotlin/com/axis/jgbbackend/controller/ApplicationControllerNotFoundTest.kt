package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.service.ApplicationService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@AutoConfigureMockMvc
@SpringBootTest
class ApplicationControllerNotFoundTest {

    var personalApplication: PersonalApplication = PersonalApplication("MLP000000000014",null,
        "2021-02-13", null,null,null,null,"840000016",
        "ETB",null,null,null,null,null,null,null,null,null,null,"PERSONAL_ETB_PA",null,
        null,null,null,null,null,null,null,"PA",null,null,null,null,"PERSONAL",null,null,null,null,null,
        "COMPLETE", null, null, null, null
    )

    @Autowired
    lateinit var service: ApplicationService

    @MockBean
    lateinit var controller: ApplicationController

    @Test
    fun testGetApplicationOfByProductCodeAndCustomerIdtNotFound() {
        Mockito.`when`(controller.getApplicationOfByProductCodeAndCustomerId("123", "123"))
            .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("No application(s) found."))

        var response: ResponseEntity<String> = service.getApplicationOfByProductCodeAndCustomerId("123","123")
        Assertions.assertEquals(response, controller.getApplicationOfByProductCodeAndCustomerId("123","123"))

        Mockito.verify(controller, times(1)).getApplicationOfByProductCodeAndCustomerId("123", "123")
    }

    @Test
    fun testGetApplicationByApplicationReferenceIdAndProductCodeNotFound() {
        Mockito.`when`(controller.getApplicationByApplicationReferenceIdAndProductCode("123", "123"))
            .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("No application(s) found."))

        var response: ResponseEntity<String> = service.getApplicationByApplicationReferenceIdAndProductCode("123","123")
        Assertions.assertEquals(response, controller.getApplicationByApplicationReferenceIdAndProductCode("123","123"))

        Mockito.verify(controller, times(1)).getApplicationByApplicationReferenceIdAndProductCode("123", "123")
    }

}