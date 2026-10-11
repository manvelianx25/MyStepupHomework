package com.mystepup.homework.client;

import com.mystepup.homework.config.ApiConfig;
import io.restassured.response.Response;

import java.math.BigDecimal;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GoodsClient {

    public Response getGoodsList() {
        return given()
                .spec(ApiConfig.basicRq())
                .queryParam("page", 0)
                .queryParam("size", 1000)
                .when()
                .get("/goods/list");
    }

    public Response createGoods(String name, BigDecimal price) {
        return given()
                .spec(ApiConfig.adminRq())
                .body(Map.of(
                        "name", name,
                        "price", price
                ))
                .when()
                .post("/goods/add");
    }
}
