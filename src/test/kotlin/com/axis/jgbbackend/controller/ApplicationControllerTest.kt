package com.axis.jgbbackend.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.FluxExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.*
import java.util.function.Consumer

@ExtendWith(SpringExtension::class)
@WebFluxTest(ProductController::class)
class JUnit5WebFluxTestAnnotationTest {
    @Autowired
    private val client: WebTestClient? = null
    private var expectedList: List<Product>? = null

    @MockBean
    private val repository: ProductRepository? = null

    @MockBean
    private val commandLineRunner: CommandLineRunner? = null
    @BeforeEach
    fun beforeEach() {
        expectedList = Arrays.asList<Product>(
            Product("1", "Big Latte", 2.99)
        )
    }

    @Test
    fun testGetAllProducts() {
        Mockito.`when`(repository.findAll()).thenReturn(Flux.fromIterable(expectedList!!))
        client
            .get()
            .uri("/products")
            .exchange()
            .expectStatus()
            .isOk
            .expectBodyList(Product::class.java)
            .isEqualTo(expectedList)
    }

    @Test
    fun testProductInvalidIdNotFound() {
        val id = "aaa"
        Mockito.`when`(repository.findById(id)).thenReturn(Mono.empty())
        client
            .get()
            .uri("/products/{id}", id)
            .exchange()
            .expectStatus()
            .isNotFound
    }

    @Test
    fun testProductIdFound() {
        val expectedProduct: Product = expectedList!![0]
        Mockito.`when`(repository.findById(expectedProduct.getId())).thenReturn(Mono.just(expectedProduct))
        client
            .get()
            .uri("/products/{id}", expectedProduct.getId())
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(Product::class.java)
            .isEqualTo(expectedProduct)
    }

    @Test
    fun testProductEvents() {
        val expectedEvent = ProductEvent(0L, "Product Event")
        val result: FluxExchangeResult<ProductEvent> = client!!.get().uri("/products/events")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .exchange()
            .expectStatus().isOk
            .returnResult(ProductEvent::class.java)
        StepVerifier.create(result.getResponseBody())
            .expectNext(expectedEvent)
            .expectNextCount(2)
            .consumeNextWith(Consumer<T> { event: T ->
                assertEquals(
                    java.lang.Long.valueOf(3),
                    event.getEventId()
                )
            })
            .thenCancel()
            .verify()
    }
}