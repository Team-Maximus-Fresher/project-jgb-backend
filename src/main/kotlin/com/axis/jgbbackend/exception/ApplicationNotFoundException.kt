package com.axis.jgbbackend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime
import java.util.*

@ControllerAdvice
public class ApplicationNotFoundException : RuntimeException() {

    @ExceptionHandler(ApplicationNotFoundException::class)
    fun handleApplicationNotFoundException(
        ex: ApplicationNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = "Application(s) not found."
        return ResponseEntity(body, HttpStatus.OK)
    }
}