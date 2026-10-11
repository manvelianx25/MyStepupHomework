package com.mystepup.homework.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

public final class ApiConfig {

    private static final String BASE_URI =
            System.getProperty(
                    "baseUri",
                    "http://localhost:8080"
            );

    private static final String LOGIN =
            System.getenv().getOrDefault("API_LOGIN", "admin");

    private static final String PASSWORD =
            System.getenv().getOrDefault("API_PASSWORD", "secret123");

    private ApiConfig() {
    }

    public static RequestSpecification basicRq() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification adminRq() {
        PreemptiveBasicAuthScheme auth =
                new PreemptiveBasicAuthScheme();

        auth.setUserName(LOGIN);
        auth.setPassword(PASSWORD);

        return new RequestSpecBuilder()
                .addRequestSpecification(basicRq())
                .setAuth(auth)
                .build();
    }

    public static String baseUri() {
        return BASE_URI;
    }
}