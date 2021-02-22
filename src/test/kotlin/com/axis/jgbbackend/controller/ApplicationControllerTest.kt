package com.axis.jgbbackend.controller

import com.axis.jgbbackend.model.ActiveSavingsAccounts
import com.axis.jgbbackend.model.ApplicationStateLog
import com.axis.jgbbackend.model.PersonalApplication
import com.axis.jgbbackend.repository.ApplicationRepo
import com.axis.jgbbackend.service.ApplicationService
import org.junit.Before
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import reactor.core.publisher.Mono
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner
import reactor.core.publisher.Flux


//@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationControllerTest {


   var client: WebTestClient? = null

    @Autowired
    val service: ApplicationService? = null
    var expectedList: MutableList<PersonalApplication>?=null

    @Before
    fun beforeEach() {

        client = WebTestClient
                .bindToController(ApplicationController())
                .configureClient()
                .baseUrl("/applications")
                .build()
        expectedList = service?.getAllPersonalLoanApplications()?.collectList()?.block() as MutableList<PersonalApplication>

    }

    @Test
    fun getAllPersonalLoanApplicationsTest() {
        client
                ?.get()
                ?.uri("/")
                ?.exchange()
                ?.expectStatus()
                ?.isOk
                ?.expectBodyList(PersonalApplication::class.java)
                ?.isEqualTo<WebTestClient.ListBodySpec<PersonalApplication>>(expectedList!!)
    }
    @Test
    fun getPersonalApplicationOfACustomerTest() {
        val expectedProduct: PersonalApplication? = expectedList?.get(0)
        client
                ?.get()
                ?.uri("/{customer_id}", expectedProduct?.customerId)
                ?.exchange()
                ?.expectStatus()
                ?.isOk
                ?.expectBody(PersonalApplication::class.java)
                ?.isEqualTo<Nothing>(expectedProduct!!)
    }
    @Test
    fun savePersonalApplicationTest() {
        val stateLog:MutableList<ApplicationStateLog> =ArrayList()
        val savingsAccounts:ActiveSavingsAccounts?=null
        var personalApplication=PersonalApplication("MLP000000000014",
                "2021-02-13", "840000016",
                "ETB", "COMPLETE", "PA", "PERSONAL","PERSONAL_ETB_PA",
                stateLog, savingsAccounts)
        client
                ?.post()
                ?.uri("/")
                ?.exchange()
                ?.expectStatus()
                ?.isOk
                ?.expectBody(PersonalApplication::class.java)
                ?.isEqualTo<Nothing>(personalApplication)

    }












}




