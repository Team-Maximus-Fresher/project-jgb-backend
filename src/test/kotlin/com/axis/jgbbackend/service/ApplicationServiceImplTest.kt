/*
package com.axis.jgbbackend.service

import com.axis.jgbbackend.model.ApplicationStateLog
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.impl.ApplicationServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import reactor.core.publisher.Mono
import java.util.*

@WebFluxTest(ApplicationServiceImpl::class)
class ApplicationServiceImplTest {

    private lateinit var expectedList: List<PersonalApplication>

    @MockBean
    private lateinit var repository: ApplicationRepo

    @BeforeEach
    fun beforeEach() {
        val stateLog:MutableList<ApplicationStateLog> = ArrayList()
        expectedList = Arrays.asList<PersonalApplication>(
            PersonalApplication("MLP000000000014",
                "2021-02-13", "840000016",
                "ETB", "COMPLETE", "PA", "PERSONAL","PERSONAL_ETB_PA",stateLog
            )
        )
    }

    @Test
    fun testGetApplicationOfACustomer()
    {
        println(expectedList[0].customerId)
        val expectedProduct = Mono.just(expectedList[0])
        println(expectedProduct)
        Mockito.`when`(
            repository.findByProductCodeAndCustomerId("PERSONAL", "840000016")
        ).thenReturn(expectedProduct)
        //println(repository.findByProductCodeAndCustomerId("PERSONAL", "840000016"))
        Assertions.assertEquals(expectedProduct, repository.findByProductCodeAndCustomerId("PERSONAL", "840000016"))
        //Mockito.verify(repository, times(1)).findByProductCodeAndCustomerId("PERSONAL", "840000016")

    }

    @Test
    fun testGetApplicationOfACustomerByApplicationReferenceId()
    {
        println(expectedList[0].customerId)
        val expectedProduct = Mono.just(expectedList[0])
        println(expectedProduct)
        Mockito.`when`(
            repository.findByApplicationIdAndProductCode("MLP000000000014", "PERSONAL")
        ).thenReturn(expectedProduct)
        //println(repository.findByApplicationIdAndProductCode("MLP000000000014","PERSONAL"))
        Assertions.assertEquals(expectedProduct, repository.findByApplicationIdAndProductCode("MLP000000000014", "PERSONAL"))
        //Mockito.verify(repository, times(1)).findByApplicationIdAndProductCode("MLP000000000014", "PERSONAL")
    }
}*/
