package ru.khehelk.rviplabs.mainservice.web.handler;

import java.net.URI;

import org.springframework.http.HttpStatus;

public record ErrorResponse (
    Integer statusCode,
    HttpStatus status,
    URI requestURI,
    String message
){
}
