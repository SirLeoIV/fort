package com.fort.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"/localhost:4200"},
        allowCredentials = "true",
        allowedHeaders = {"X-Custom-Header"},
        methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class HealthController {

    @GetMapping("/")
    public String health() {
        return "{\"response\": \"ok\"}";
    }
}
