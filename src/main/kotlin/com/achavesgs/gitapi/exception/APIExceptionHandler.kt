package com.achavesgs.gitapi.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class APIExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(UserNotFoundException::class)])
    fun userNotFound(exception: UserNotFoundException, request: WebRequest): ResponseEntity<Any> {
        println("Handle")
        return handleExceptionInternal(exception, "Profile not found", HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(value = [(APIException::class)])
    fun apiException(exception: APIException, request: WebRequest): ResponseEntity<Any> {
        println("Handle")
        return handleExceptionInternal(exception, exception.message, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

    @ExceptionHandler(value = [(NotFoundException::class)])
    fun notFoundException(exception: NotFoundException, request: WebRequest): ResponseEntity<Any> {
        println("Handle")
        return handleExceptionInternal(exception, "Not Found", HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }
}