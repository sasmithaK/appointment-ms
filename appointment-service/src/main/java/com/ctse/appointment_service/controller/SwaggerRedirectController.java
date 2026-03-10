package com.ctse.appointment_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Redirects root and /swagger-ui.html to Swagger UI so the docs are easy to find.
 */
@Controller
public class SwaggerRedirectController {

    @GetMapping({"/", "/swagger-ui.html"})
    public String redirectToSwaggerUi() {
        return "redirect:/swagger-ui/index.html";
    }
}
