package com.tosan.tools.mask.integration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author mina khoshnevisan
 * @since 7/5/2022
 */
@SpringBootApplication
public class TestMaskApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(TestMaskApplication.class)
                .web(WebApplicationType.SERVLET);
        app.run();
    }
}